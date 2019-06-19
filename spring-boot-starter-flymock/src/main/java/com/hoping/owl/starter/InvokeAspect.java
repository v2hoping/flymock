package com.hoping.owl.starter;

import com.hoping.owl.flymock.FlyMockException;
import com.hoping.owl.flymock.TypeReference;
import com.hoping.owl.flymock.object.Template;
import com.hoping.owl.flymock.object.TemplateMock;
import com.hoping.owl.starter.model.FlyMock;
import com.hoping.owl.starter.model.MatchingMode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by houping wang on 2019/4/23
 *
 * @author houping wang
 */
@Aspect
public class InvokeAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvokeAspect.class);

    @Pointcut("@annotation(flyMock)")
    public void invokerMethod(FlyMock flyMock) {
    }

    @Pointcut(value = "@within(flyMock))")
    public void invokerClass(FlyMock flyMock) {
    }

    @Around(value = "invokerClass(flyMock)||invokerMethod(flyMock)", argNames = "pjp,flyMock")
    public Object handleAround(ProceedingJoinPoint pjp, FlyMock flyMock) throws Throwable {
        //获得返回值类型
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Object returnValue = null;
        Object actualValue = null;
        if(flyMock == null) {
            Class declaringType = signature.getDeclaringType();
            Annotation annotation = declaringType.getAnnotation(FlyMock.class);
            if(annotation == null) {
                throw new FlyMockException(declaringType.getName() + "中FlyMock注解不存在，请检查！");
            }
            flyMock = (FlyMock) annotation;
        }
        if (flyMock.matchingMode() == MatchingMode.ALL) {
            returnValue = mock(method);
        } else {
            actualValue = pjp.proceed();
            if (null == actualValue) {
                returnValue = mock(method);
            }else{
                returnValue = actualValue;
            }
        }
        if (LOGGER.isDebugEnabled()) {
            if (flyMock.matchingMode() == MatchingMode.ALL) {
                LOGGER.debug("[FlyMock拦截]模式:全拦截,方法:{}#{},入参:{},Mock返参:{}", method.getDeclaringClass().getName(),
                        method.getName(), pjp.getArgs(), returnValue == null ? null : returnValue.toString());
            } else {
                LOGGER.debug("[FlyMock拦截]模式:NULL匹配拦截,方法:{}#{},入参:{},真实返回值:{},Mock返参:{}", method.getDeclaringClass().getName(),
                        method.getName(), pjp.getArgs(), actualValue, returnValue == null ? null : returnValue.toString());
            }
        }
        return returnValue;
    }

    private Object mock(Method method) {
        Type genericReturnType = method.getGenericReturnType();
        Template<?> template = TemplateMock.template(new TypeReference<>(genericReturnType, null));
        return template.mockType();
    }


}
