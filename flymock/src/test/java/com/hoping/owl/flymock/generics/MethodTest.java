package com.hoping.owl.flymock.generics;

import com.alibaba.fastjson.JSON;
import com.hoping.owl.flymock.TypeReference;
import com.hoping.owl.flymock.model.UserT;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.TypeVariableImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by houping wang on 2019/4/12
 *
 * @author houping wang
 */
public class MethodTest {

    @Test
    public void test2() {
        TypeReference<UserT<String,Integer>> typeReference = new TypeReference<UserT<String,Integer>>() {};
        ParameterizedType type = (ParameterizedType)typeReference.getType();
        Class<?> clz = (Class<?>)type.getRawType();
        Field[] fields = clz.getDeclaredFields();
        List<Type> types = new ArrayList<>();
        Method[] methods = clz.getMethods();
        for(Method method : methods) {
            if(method.getName().startsWith("getT")) {
                Type genericReturnType = method.getGenericReturnType();
                TypeVariableImpl typeVariable = (TypeVariableImpl) method.getGenericReturnType();
                System.out.println();
            }
            if(!method.getName().startsWith("setT")) {
                continue;
            }
//            Type[] genericParameterTypes = method();
//            ParameterizedType typeVariable = (ParameterizedType) genericParameterTypes[0];

            System.out.println();
        }
        for(Field field : fields) {
            Type genericType = field.getGenericType();
            types.add(genericType);
            System.out.println(genericType);
        }
    }

    @Test
    public void test3() throws NoSuchFieldException {
        Integer var = 1;
        Class<? extends Integer> aClass = var.getClass();
        Field[] fields = aClass.getFields();
    }

    @Test
    public void test4() {
        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        list.add("11ffs");
        set.add("ffffffdddd");
        Date date = new Date();
        String a = "{\"time\":1555058552443,\"time1\":1555058552443,\"time2\":1555058552455}";
    }

    @Test
    public void test5() {
        String s = JSON.toJSONString(null);
        Object aNull = JSON.parse("null");
    }

    public List<String> listT() {
        return null;
    }

    public List list() {
        return null;
    }

    public UserT<String,Integer> userT() {
        return null;
    }

    public void setList(List<String> list) {
    }

}
