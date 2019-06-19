package com.hoping.owl.flymock.object;


import com.hoping.owl.flymock.TypeReference;

/**
 * Created by houping wang on 2019/4/26
 * 类型转换执行者
 * 将对象类型转换对应的模板占位符
 * @author houping wang
 */
public interface TypeConverterExecutor<T> {



    Object convert(TypeReference<T> t);
}
