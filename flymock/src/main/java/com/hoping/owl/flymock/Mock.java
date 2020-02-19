package com.hoping.owl.flymock;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hoping.owl.flymock.placeholder.MessagePlaceholderFormat;
import com.hoping.owl.flymock.placeholder.PlaceholderHandle;
import com.hoping.owl.flymock.placeholder.manager.LocalPlaceholderManager;
import com.hoping.owl.flymock.strategy.KeyWrapper;
import com.hoping.owl.flymock.strategy.Node;
import com.hoping.owl.flymock.strategy.Strategy;
import com.hoping.owl.flymock.strategy.ValueWrapper;

/**
 * Created by houping wang on 2019/3/9
 * 模板和数据生成调用类，聚合生成模拟数据、生成模拟模板、生成类的模拟对象功能
 *
 * @author houping wang
 */
public class Mock {

    /**
     * 根据template模板产生模拟数据
     *
     * @param template 模板JSON
     * @return 模拟数据
     */
    public static Object mock(Object template) {
        if (template == null) {
            return null;
        }
        Object obj = template;
        if (template instanceof String) {
            String templateStr = (String) template;
            try {
                if (templateStr.startsWith("{") && templateStr.endsWith("}")) {
                    obj = JSON.parseObject((String) template);
                } else if (templateStr.startsWith("[") && templateStr.endsWith("]")) {
                    obj = JSON.parseArray((String) template);
                }
            } catch (Exception e) {
            }
        }
        KeyWrapper keyWrapper = new KeyWrapper("default");
        ValueWrapper valueWrapper = new ValueWrapper(obj);
        Node<String, Object> node = Strategy.keyStrategy(keyWrapper, valueWrapper);
        return node.getValue();
    }

    public static <T> T mock(Object template, TypeReference<T> t) {
        Object mockResult = mock(template);
        String s = JSON.toJSONString(mockResult);
        Object object = JSONObject.parseObject(s, t.getType());
        return (T)object;
    }

    public static String mockToJson(Object template) {
        Object mockResult = mock(template);
        return JSON.toJSONString(mockResult);
    }

    /**
     * 判断clz是否是基本类型 或者 基本类型的包装类型
     *
     * @param clz 类型
     * @return true/false
     */
    private static boolean isPrimitiveOrWrapClass(Class clz) {
        return clz.isPrimitive() || isWrapClass(clz);
    }

    private static boolean isWrapClass(Class clz) {
        try {
            return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

    public static <T> void put(PlaceholderHandle<T> placeholderHandle) {
        MessagePlaceholderFormat.put(placeholderHandle);
    }
}
