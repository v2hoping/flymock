package com.hoping.owl.flymock;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by houping wang on 2019/4/15
 *
 * @author houping wang
 */
public class MockConfig {

    /**
     * 根据类模板规则
     */
    private static Map<String, String> classTemplateMap = new HashMap<>();

    /**
     * 根据类-字段规则
     */
    private static Map<String, String> classFiledStrategyMap = new HashMap<>();

    /**
     * 根据全局—字段规则
     */
    private static Map<String, String> globalFiledStrategyMap = new HashMap<>();

    /**
     * 添加类模板，
     * @param classKey
     * @param template
     */
    public void addClassTemplate(String classKey, String template) {

    }


}
