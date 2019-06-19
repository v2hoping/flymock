package com.hoping.owl.starter.model;

/**
 * Created by houping wang on 2019/4/24
 *
 * @author houping wang
 */
public enum MatchingMode {

    /**
     * NULL值匹配模式，只有返回NULL值时才Mock
     */
    NULL,
    /**
     * 拦截所有返回值
     */
    ALL;
}
