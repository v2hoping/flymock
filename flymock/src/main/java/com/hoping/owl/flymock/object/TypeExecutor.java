package com.hoping.owl.flymock.object;

import com.hoping.owl.flymock.annotation.FiledMock;

/**
 * Created by houping wang on 2019/4/26
 *
 * @author houping wang
 */
public interface TypeExecutor<T> {

    Object booleanType(Class<T> rawClass, FiledMock filedMock);

    Object characterType(Class<T> rawClass, FiledMock filedMock);

    Object integerType(Class<T> rawClass, FiledMock filedMock);

    Object shortType(Class<T> rawClass, FiledMock filedMock);

    Object byteType(Class<T> byteClass, FiledMock filedMock);

    Object longType(Class<T> longClass, FiledMock filedMock);

    Object floatType(Class<T> floatClass, FiledMock filedMock);

    Object doubleType(Class<T> doubleClass, FiledMock filedMock);


//        //date/Calendar/rawClass
//        //枚举
//        //字符串
//        //BigDecimal
//        //BigInteger
//    Class<?> rawClass = t.getActualClass();
//    Type[] argTypes = t.getArgTypes();
//        if(rawClass == null) {
//        return null;
//    }
//    //基本类型处理
//        if (isPrimitiveOrWrapClass(rawClass)) {
//        return primitiveTemplateHandle(rawClass);
//    }
//    //枚举
//        if (rawClass.isEnum()) {
//        return enumTemplateHandle(rawClass);
//    }
//    //字符串类型
//        if (rawClass == String.class) {
//        return stringTemplate(rawClass);
//    }
//    //BigDecimal
//        if (rawClass == BigDecimal.class) {
//        return bigDecimalTemplateHandle(rawClass);
//    }
//    //BigInteger
//        if (rawClass == BigInteger.class) {
//        return bigIntegerTemplateHandle(rawClass);
//    }
//    //Timestamp
//        if (rawClass == Timestamp.class) {
//        return timestampTemplateHandle(rawClass);
//    }
//    //Date类型
//        if (rawClass == Date.class) {
//        return dateTemplateHandle(rawClass);
//    }
//    //Calendar类型
//        if (rawClass == Calendar.class) {
//        return calendarTemplateHandle(rawClass);
//    }
//    //数组类型
//        if (rawClass.isArray()) {
//        return arrayTemplateHandle(rawClass, t);
//    }
//    //集合类型
//        if (Collection.class.isAssignableFrom(rawClass)) {
//        return collectionTemplateHandle(t, argTypes);
//    }
//    //Map类型
//        if (Map.class.isAssignableFrom(rawClass)) {
//        return mapTemplateHandle(t, argTypes);
//    }
//    //接口、注解 排除
//        if (rawClass.isInterface() || rawClass.isAnnotation()) {
//        return null;
//    }
//    //复杂对象
//    Method[] methods = rawClass.getMethods();
//    JSONObject instance = new JSONObject();
//        for (Method method : methods) {
//        String name = method.getName();
//        Type[] parameterTypes = method.getGenericParameterTypes();
//        if (!name.startsWith("set") || parameterTypes.length != 1) {
//            continue;
//        }
//        //TODO:解析set后缀，获得字段上注解，拿到key策略和自定义占位符
//        String key = name.substring(3, name.length()).toLowerCase();
//        Type paramType = parameterTypes[0];
//        Object object = null;
//        if (paramType instanceof GenericArrayType) {
//            object = arrayTemplateHandelT(paramType, t);
//        } else {
//            object = jsonTemplate(new TypeReference<>(paramType, t));
//        }
//        //TODO:这里需要切入key的策略, 自定义占位符fastjson解析错误怎么办？？？类型与原数据不匹配
//        instance.put(key, object);
//    }
//        return instance;

}
