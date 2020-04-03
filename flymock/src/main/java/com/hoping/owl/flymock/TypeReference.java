package com.hoping.owl.flymock;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

/**
 * 类型引用
 *
 * @author houping wang
 */
public class TypeReference<T> {

    /**
     * 策略Map
     */
    private Map<String, String> keyStrategyMap;

    /**
     * 类型默认模板
     */
    private String defaultTemplate;

    /**
     * T 带泛型的类型
     */
    protected final Type type;
    /**
     * T 的原始类型
     */
    protected Type rawType;
    /**
     * T 的泛型类型
     */
    protected Type[] argTypes;

    protected Map<String, TypeReference> map;

    private volatile Constructor<?> constructor;

    private TypeReference prentTypeReference;

    protected TypeReference() {
        // 只有通过取 GenericSuperclass 才能拿到泛型类型，所以将构造函数的访问级别设置成 protected
        Type superClass = getClass().getGenericSuperclass();
        type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
        map = new HashMap<>();
        if (type instanceof ParameterizedType) {
            rawType = ((ParameterizedType) type).getRawType();
            argTypes = ((ParameterizedType) type).getActualTypeArguments();
            Class<?> classType = (Class<?>) rawType;
            TypeVariable<? extends Class<?>>[] typeParameters = classType.getTypeParameters();
            for (int i = 0; i < typeParameters.length; i++) {
                map.put(typeParameters[i].getName(), new TypeReference(argTypes[i], this) {
                });
            }
        }
    }

    public TypeReference(Type type, TypeReference prentTypeReference) {
        this.prentTypeReference = prentTypeReference;
        map = new HashMap<>();
        //泛型类型，需要从父级获取真实类型
        if (type instanceof TypeVariable) {
            TypeReference typeReference = prentTypeReference.getMapType(type);
            this.type = typeReference.getType();
            this.argTypes = typeReference.getArgTypes();
            this.map = typeReference.getMap();
            this.rawType = typeReference.getRawType();
            return;
        }
        //Class类型直接填充
        this.type = type;
        //参数类型，获取真实类型，以及泛型和真实类型映射
        if (type instanceof ParameterizedType) {
            rawType = ((ParameterizedType) type).getRawType();
            argTypes = ((ParameterizedType) type).getActualTypeArguments();
            Class<?> classType = (Class<?>) rawType;
            TypeVariable<? extends Class<?>>[] typeParameters = classType.getTypeParameters();
            for (int i = 0; i < typeParameters.length; i++) {
                if (argTypes[i] instanceof TypeVariable) {
                    if (prentTypeReference != null) {
                        map.put(typeParameters[i].getName(), prentTypeReference.getMapType(argTypes[i]));
                    }
                } else {
                    map.put(typeParameters[i].getName(), new TypeReference(argTypes[i], this));
                }
            }
        }
    }

    /**
     * 检查嵌套循环,嵌套次数大于1则返回false
     * @return 返回是否多次嵌套
     */
    public boolean checkNestTwo() {
        return this.checkNest(this) < 2;
    }

    public int checkNest(TypeReference typeReference) {
        int count = 0;
        if(prentTypeReference == null) {
            return count;
        }
        Class currentClass = typeReference.getActualClass();
        Class parentClass = prentTypeReference.getActualClass();
        if(currentClass == parentClass) {
            count ++;
        }
        count += prentTypeReference.checkNest(typeReference);
        return count;
    }

    protected TypeReference(Class<T> clz, TypeReference prentTypeReference) {
        this.type = clz;
        this.prentTypeReference = prentTypeReference;
        map = new HashMap<>();
    }

    public Class<?> getActualClass() {
        if (rawType != null) {
            return (Class) rawType;
        } else {
            if(type instanceof Class) {
                return (Class) type;
            }else{
                return null;
            }
        }
    }

    public T newInstance() {
        try {
            Class<?> rawType = type instanceof Class<?>
                    ? (Class<?>) type
                    : (Class<?>) ((ParameterizedType) type).getRawType();
            return (T) rawType.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new FlyMockException(e.getMessage(), e);
        }
    }

    public Type getType() {
        return type;
    }

    public Type getRawType() {
        return rawType;
    }

    public Type[] getArgTypes() {
        return argTypes;
    }

    public Map<String, TypeReference> getMap() {
        return map;
    }

    public TypeReference getMapType(Type paramType) {
        if (paramType instanceof TypeVariable) {
            if (map != null && map.size() > 0) {
                return map.get(((TypeVariable) paramType).getName());
            }
        }
        return new TypeReference<>(Object.class, this);
    }

    public Map<String, String> getKeyStrategyMap() {
        return keyStrategyMap;
    }

    public String getDefaultTemplate() {
        return defaultTemplate;
    }

    public Constructor<?> getConstructor() {
        return constructor;
    }

    public TypeReference getPrentTypeReference() {
        return prentTypeReference;
    }

    public void setKeyStrategyMap(Map<String, String> keyStrategyMap) {
        this.keyStrategyMap = keyStrategyMap;
    }

    public void setDefaultTemplate(String defaultTemplate) {
        this.defaultTemplate = defaultTemplate;
    }
}