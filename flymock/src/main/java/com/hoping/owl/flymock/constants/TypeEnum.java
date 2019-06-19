package com.hoping.owl.flymock.constants;

/**
 * Created by houping wang on 2019/5/9
 * 抽象类型枚举
 * 将Java中多种类型归类为字符串、整数、布尔、浮点数、日期、字符
 * @author houping wang
 */
public enum TypeEnum {

    /**
     * 字符串类型
     */
    STRING,
    /**
     * 整数类型
     */
    NUMBER,
    /**
     * 浮点类型
     */
    FLOAT,
    /**
     * 布尔类型
     */
    BOOLEAN,
    /**
     * 时间类型
     */
    DATE,
    /**
     * 字符类型
     */
    CHAR,
    /**
     * 枚举类型
     */
    ENUM,
    /**
     * 其他对象类型
     */
    OBJECT;
}
