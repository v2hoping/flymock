package com.hoping.owl.starter;

import com.hoping.owl.flymock.placeholder.MessagePlaceholderFormat;
import com.hoping.owl.flymock.placeholder.PlaceholderHandle;
import com.hoping.owl.flymock.placeholder.manager.AbstractPlaceholderManager;
import com.hoping.owl.flymock.util.ClassUtil;
import com.hoping.owl.starter.util.SpringRegisterUtil;
import com.hoping.owl.starter.util.SpringUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by houping wang on 2019/4/23
 *
 * @author houping wang
 */
@EnableConfigurationProperties(FlyMockProperties.class)
@ConditionalOnProperty(name = "spring.fly.mock.enable", havingValue = "true", matchIfMissing = true)
public class FlyMockAutoConfiguration implements BeanPostProcessor,CommandLineRunner {

    private Set<PlaceholderHandle> placeholderHandles = new HashSet<>();

    @Bean
    public SpringUtil springUtil() {
        return new SpringUtil();
    }

    @Bean
    public InvokeAspect invokeAspect() {
        return new InvokeAspect();
    }

    @Bean
    public RegisterSpringPlaceholderBean registerSpringPlaceholderBean() {
        return new RegisterSpringPlaceholderBean();
    }

    @Bean
    public SpringPlaceholderManager springPlaceholderManager() {
        return new SpringPlaceholderManager();
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> aClass = bean.getClass();
        if(PlaceholderHandle.class.isAssignableFrom(aClass)) {
            placeholderHandles.add((PlaceholderHandle) bean);
        }
        return bean;
    }

    @Override
    public void run(String... args) throws Exception {
        //获得spring占位符管理器
        SpringPlaceholderManager springPlaceholderManager = SpringUtil.getBean(SpringPlaceholderManager.class);
        //加载自定义bean
        springPlaceholderManager.addAll(placeholderHandles);
        //设置spring占位符为处理器
        MessagePlaceholderFormat.setPlaceholderManager(springPlaceholderManager);
    }

    private static class RegisterSpringPlaceholderBean {

        @PostConstruct
        public void init() {
            Set<Class<?>> classSet = ClassUtil.getByInterface(PlaceholderHandle.class, new String[]{"com.hoping.owl.flymock.placeholder.handle"});
            for(Class<?> clazz : classSet) {
                SpringRegisterUtil.registerBean(clazz.getSimpleName(), clazz);
            }
        }
    }

    public class SpringPlaceholderManager extends AbstractPlaceholderManager {

        @Override
        public void init() {
            Map<String, PlaceholderHandle> beans = SpringUtil.getBeans(PlaceholderHandle.class);
            for(Map.Entry<String, PlaceholderHandle> entry : beans.entrySet()) {
                try {
                    hashMap.put(entry.getValue().key(), entry.getValue());
                } catch (Exception e) {
                    LOGGER.error("[加载Spring占位符处理器失败]" + e.getMessage(), e);
                }

            }
        }

    }
}
