package com.hoping.owl.flymock.placeholder;

import com.hoping.owl.flymock.TypeReference;
import com.hoping.owl.flymock.placeholder.handle.CityHandle;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by houping wang on 2019/5/7
 *
 * @author houping wang
 */
public abstract class AbstractPlaceholderHandle<T> implements PlaceholderHandle<T> {

    protected AbstractPlaceholderHandle() {
    }

    @Override
    public Class returnClassType() {
        // 只有通过取 GenericSuperclass 才能拿到泛型类型，所以将构造函数的访问级别设置成 protected
        Type superClass = getClass().getGenericSuperclass();
        Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getRawType();
        }
        return null;
    }
}
