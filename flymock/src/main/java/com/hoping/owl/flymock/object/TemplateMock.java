package com.hoping.owl.flymock.object;

import com.alibaba.fastjson.JSONObject;
import com.hoping.owl.flymock.FlyMockException;
import com.hoping.owl.flymock.FlyRandom;
import com.hoping.owl.flymock.TypeReference;
import com.hoping.owl.flymock.annotation.FiledMock;
import com.hoping.owl.flymock.constants.TypeEnum;
import com.hoping.owl.flymock.placeholder.MessagePlaceholderFormat;
import com.hoping.owl.flymock.strategy.StrategyUtil;
import com.hoping.owl.flymock.util.ClassUtil;
import com.hoping.owl.flymock.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by houping wang on 2019/4/11
 *
 * @author houping wang
 */
public class TemplateMock {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateMock.class);

    private static <T> Object jsonTemplate(Class<?> cls, TypeReference<?> typeReference) {
        return TemplateMock.jsonTemplate(new TypeReference<T>(cls, typeReference) {
        });
    }

    private static <T> Object jsonTemplate(TypeReference<T> t) {
        //初始化所有类型，包含泛型、真实类型、包裹泛型
        Class<?> rawClass = t.getActualClass();
        Type[] argTypes = t.getArgTypes();
        if (rawClass == null) {
            return null;
        }
        //基本类型处理
        if (isPrimitiveOrWrapClass(rawClass)) {
            return primitiveTemplateHandle(rawClass);
        }
        //枚举
        if (rawClass.isEnum()) {
            return enumTemplateHandle(rawClass);
        }
        //字符串类型
        if (rawClass == String.class) {
            return stringTemplate(rawClass);
        }
        //BigDecimal
        if (rawClass == BigDecimal.class) {
            return bigDecimalTemplateHandle(rawClass);
        }
        //BigInteger
        if (rawClass == BigInteger.class) {
            return bigIntegerTemplateHandle(rawClass);
        }
        //Timestamp
        if (rawClass == Timestamp.class) {
            return timestampTemplateHandle(rawClass);
        }
        //Date类型
        if (rawClass == Date.class) {
            return dateTemplateHandle(rawClass);
        }
        //Calendar类型
        if (rawClass == Calendar.class) {
            return calendarTemplateHandle(rawClass);
        }
        //数组类型
        if (rawClass.isArray()) {
            return arrayTemplateHandle(rawClass, t);
        }
        //集合类型
        if (Collection.class.isAssignableFrom(rawClass)) {
            return collectionTemplateHandle(t, argTypes);
        }
        //Map类型
        if (Map.class.isAssignableFrom(rawClass)) {
            return mapTemplateHandle(t, argTypes);
        }
        //接口、注解 排除
        if (rawClass.isInterface() || rawClass.isAnnotation()) {
            return null;
        }
        //复杂对象
        Method[] methods = rawClass.getMethods();
        JSONObject instance = new JSONObject();
        for (Method method : methods) {
            String name = method.getName();
            Field field = null;
            Type[] parameterTypes = method.getGenericParameterTypes();
            if (!name.startsWith("set") || parameterTypes.length != 1) {
                continue;
            }
            String fieldName = StringUtil.lowerFirst(name.substring(3, name.length()));
            try {
                field = rawClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                continue;
            }
            if (field == null) {
                continue;
            }
            FiledMock filedMock = field.getAnnotation(FiledMock.class);
            String key = fieldName;
            if (filedMock != null) {
                //设置策略key
                key = StrategyUtil.addKeyStrategy(fieldName, filedMock.strategy());
                String newValue = filedMock.value();
                //检查value类型与占位符返回类型是否一致
                MessagePlaceholderFormat messagePlaceholderFormat = new MessagePlaceholderFormat(newValue);
                //占位符类型
                Class placeholderClass = messagePlaceholderFormat.returnType();
                //返回值真实类型
                Class actualClass = new TypeReference(field.getGenericType(), t).getActualClass();
                if(matchingPlaceholderAndReturnClass(placeholderClass, actualClass)) {
                    instance.put(key, newValue);
                    continue;
                }
            }
            Object value = null;
            Type paramType = parameterTypes[0];
            if (paramType instanceof GenericArrayType) {
                value = arrayTemplateHandelT(paramType, t);
            } else {
                value = jsonTemplate(new TypeReference<>(paramType, t));
            }
            instance.put(key, value);
        }
        return instance;
    }

    /**
     * 检查placeholderClass和actualClass是否匹配
     *
     * @param placeholderClass 站位符返回类型
     * @param actualClass      字段类型
     * @return true/false
     */
    private static boolean matchingPlaceholderAndReturnClass(Class placeholderClass, Class actualClass) {
        if(placeholderClass == null || actualClass == null) {
            return false;
        }
        TypeEnum placeholderClassEnum = ClassUtil.getTypeEnum(placeholderClass);
        TypeEnum actualClassEnum = ClassUtil.getTypeEnum(actualClass);
        if(placeholderClassEnum == actualClassEnum) {
            return true;
        }
        //枚举兼容String
        if(actualClassEnum == TypeEnum.ENUM && placeholderClassEnum == TypeEnum.STRING) {
            return true;
        }
        //时间类型兼容整数
        if(actualClassEnum == TypeEnum.DATE && placeholderClassEnum == TypeEnum.NUMBER) {
            return true;
        }
        //浮点型兼容整数
        if(actualClassEnum == TypeEnum.FLOAT && placeholderClassEnum == TypeEnum.NUMBER) {
            return true;
        }
        return false;
    }

    private static <T> Object jsonTemplate(Type type, TypeReference<?> parentType) {
        return TemplateMock.jsonTemplate(new TypeReference<T>(type, parentType) {});
    }

    public static <T> Template<T> template(TypeReference<T> t) {
        return new Template<>(jsonTemplate(t), t);
    }

    public static <T> T mock(TypeReference<T> t) {
        return new Template<>(jsonTemplate(t), t).mockType();
    }

    public static <T> String json(TypeReference<T> t) {
        return new Template<>(jsonTemplate(t), t).mockToJson();
    }

    /**
     * 处理泛型数组
     */
    private static <T> Object arrayTemplateHandelT(Type type, TypeReference prentTypeReference) {
        //无法找到真实类型的泛型，返回null
        if (prentTypeReference.getMap() == null || prentTypeReference.getMap().size() == 0) {
            return null;
        }
        Object o = Array.newInstance(Object.class, 1);
        if (type instanceof GenericArrayType) {
            Array.set(o, 0, arrayTemplateHandelT(((GenericArrayType) type).getGenericComponentType(), prentTypeReference));
            return o;
        } else {
            return jsonTemplate(type, prentTypeReference);
        }
    }

    private static <T> Object mapTemplateHandle(TypeReference<?> parentType, Type[] argTypes) {
        if (argTypes == null || argTypes.length != 2) {
            return null;
        }
        Class<?> targetClass = parentType.getActualClass();
        //获取内部泛型k v
        Method put = null;
        try {
            put = targetClass.getMethod("put", Object.class, Object.class);
        } catch (NoSuchMethodException e) {
            throw new FlyMockException("无法获取add方法" + e.getMessage());
        }
        Type keyType = put.getGenericParameterTypes()[0];
        Type valueType = put.getGenericParameterTypes()[1];
        Map map = new HashMap();
        TypeReference<Object> keyTypeReference = new TypeReference<>(keyType, parentType);
        if (!isKey(keyTypeReference.getActualClass())) {
            return map;
        }
        //key
        String key = Objects.requireNonNull(primitiveKeyHandler(keyTypeReference.getActualClass())).toString();
        Object value = jsonTemplate(valueType, parentType);
        map.put(key, value);
        return map;
    }

    private static <T> Object collectionTemplateHandle(TypeReference<?> parentType, Type[] argTypes) {
        Class<?> targetClass = parentType.getActualClass();
        Collection instance = null;
        if (targetClass.isInterface()) {
            if (List.class == targetClass) {
                instance = new ArrayList<>();
            } else if (Set.class == targetClass) {
                instance = new HashSet<>();
            } else {
                //默认collection
                instance = new ArrayList<>();
            }
        } else {
            try {
                instance = (Collection) targetClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (instance == null) {
            return null;
        }
        if (argTypes == null || argTypes.length == 0) {
            return instance;
        }
        Method add = null;
        try {
            add = targetClass.getMethod("add", Object.class);
        } catch (NoSuchMethodException e) {
            throw new FlyMockException("无法获取add方法" + e.getMessage());
        }
        Type genericReturnType = add.getGenericParameterTypes()[0];
        Object obj = jsonTemplate(genericReturnType, parentType);
        instance.add(obj);
        return instance;
    }

    private static <T> String primitiveTemplateHandle(Class<T> clazz) {
        String className = clazz.getName();
        switch (className) {
            case "java.lang.Boolean":
            case "boolean":
                return "@boolean()";
            case "java.lang.Character":
            case "char":
                return "@character()";
            case "java.lang.Byte":
            case "byte":
                return "@integer(0,127)";
            case "java.lang.Short":
            case "short":
                return "@integer(0,127)";
            case "java.lang.Integer":
            case "int":
                return "@integer(0,127)";
            case "java.lang.Long":
            case "long":
                return "@integer(0,127)";
            case "java.lang.Float":
            case "float":
                return "@float(0,127,3)";
            case "java.lang.Double":
            case "double":
                return "@float(0,127,3)";
            default:
                throw new FlyMockException(className + "不支持该基础类型");
        }
    }

    private static boolean isKey(Class<?> rawClass) {
        //基本类型处理
        if (isPrimitiveOrWrapClass(rawClass)) {
            return true;
        }
        //字符串类型
        if (rawClass == String.class) {
            return true;
        }
        //BigDecimal
        if (rawClass == BigDecimal.class) {
            return true;
        }
        //BigInteger
        if (rawClass == BigInteger.class) {
            return true;
        }
        //Timestamp
        if (rawClass == Timestamp.class) {
            return true;
        }
        //Date类型
        if (rawClass == Date.class) {
            return true;
        }
        //Calendar类型
        if (rawClass == Calendar.class) {
            return true;
        }
        return false;
    }

    private static <T> Object primitiveKeyHandler(Class<T> clazz) {
        String className = clazz.getName();
        //基本类型处理
        if (isPrimitiveOrWrapClass(clazz)) {
            switch (className) {
                case "java.lang.Boolean":
                case "boolean":
                    return FlyRandom.booleanRandom();
                case "java.lang.Character":
                case "char":
                    return FlyRandom.character();
                case "java.lang.Byte":
                case "byte":
                    return FlyRandom.integer(0, 127);
                case "java.lang.Short":
                case "short":
                    return FlyRandom.integer(0, 127);
                case "java.lang.Integer":
                case "int":
                    return FlyRandom.integer(0, 127);
                case "java.lang.Long":
                case "long":
                    return FlyRandom.integer(0, 127);
                case "java.lang.Float":
                case "float":
                    return FlyRandom.doubleRandom(0, 127, 3);
                case "java.lang.Double":
                case "double":
                    return FlyRandom.doubleRandom(0, 127, 3);
                default:
                    throw new FlyMockException(className + "不支持该基础类型");
            }
        }
        //字符串类型
        if (clazz == String.class) {
            return FlyRandom.string(3, 8);
        }
        //BigDecimal
        if (clazz == BigDecimal.class) {
            return FlyRandom.integer(0, 127);
        }
        //BigInteger
        if (clazz == BigInteger.class) {
            return FlyRandom.integer(0, 127);
        }
        //Timestamp
        if (clazz == Timestamp.class) {
            return FlyRandom.timeStamp();
        }
        //Date类型
        if (clazz == Date.class) {
            return FlyRandom.timeStamp();
        }
        //Calendar类型
        if (clazz == Calendar.class) {
            return FlyRandom.timeStamp();
        }
        return null;

    }

    private static <T> String enumTemplateHandle(Class<T> clazz) {
        T[] enumConstants = clazz.getEnumConstants();
        if (enumConstants == null || enumConstants.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("@enum(");
        for (int i = 0; i < enumConstants.length; i++) {
            sb.append(enumConstants[i].toString());
            if (i != enumConstants.length - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    private static <T> String stringTemplate(Class<T> clazz) {
        return "@string()";
    }

    private static <T> String bigDecimalTemplateHandle(Class<T> clazz) {
        return "@float(0,127,3)";
    }

    private static <T> String bigIntegerTemplateHandle(Class<T> clazz) {
        return "@float(0,127)";
    }

    private static <T> String timestampTemplateHandle(Class<T> clazz) {
        return "@timestamp()";
    }

    private static <T> String dateTemplateHandle(Class<T> clazz) {
        return "@timestamp()";
    }

    private static <T> String calendarTemplateHandle(Class<T> clazz) {
        return "@timestamp()";
    }

    private static <T> Object arrayTemplateHandle(Class<T> clazz, TypeReference<?> parentTypeReference) {
        Class<?> componentType = clazz.getComponentType();
        Object array = Array.newInstance(Object.class, 1);
        Object mock = TemplateMock.jsonTemplate(componentType, parentTypeReference);
        Array.set(array, 0, mock);
        return array;
    }

    /**
     * 判断是否是基础类型和装箱类型
     * byte(字节)、short(短整型)、char(字符型)、int(整型)、float(单精度型/浮点型)、long(长整型)、double(双精度型)、boolean(布尔)
     *
     * @param clz class
     * @return true/false
     */
    private static boolean isPrimitiveOrWrapClass(Class clz) {
        return clz.isPrimitive() || isWrapPrimitiveClass(clz);
    }

    private static boolean isWrapPrimitiveClass(Class clz) {
        try {
            return ((Class<?>) clz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isSupportRandomMap(Class clazz) {
        if (Collection.class.isAssignableFrom(clazz)) {
            return false;
        }
        if (clazz.isArray()) {
            return false;
        }
        if (Map.class.isAssignableFrom(clazz)) {
            return false;
        }
        if (clazz.isInterface()) {
            return false;
        }
        if (Map.class.isAssignableFrom(clazz)) {
            return false;
        }
        return true;
    }

}
