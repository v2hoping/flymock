package com.hoping.owl.flymock.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hoping.owl.flymock.FlyMockException;
import com.hoping.owl.flymock.placeholder.MessagePlaceholderFormat;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by houping wang on 2019/4/4
 * value解析器
 *
 * @author houping wang
 */
public class ValueResolver {

    /**
     * 根据策略、占位符生成随机值
     *
     * @param valueWrapper 占位符
     * @return 随机值
     */
    public static Object resolver(ValueWrapper valueWrapper) {
        Object originValue = valueWrapper.getOriginValue();
        if (originValue instanceof String) {
            return MessagePlaceholderFormat.format((String) valueWrapper.getOriginValue());
        } else if (originValue instanceof BigDecimal) {
            return originValue;
        } else if (originValue instanceof Boolean) {
            return originValue;
        } else if (originValue instanceof JSONArray) {
            JSONArray array = (JSONArray) originValue;
            JSONArray newArray = new JSONArray();
            for (Object anArray : array) {
                newArray.add(resolver(new ValueWrapper(anArray)));
            }
            return newArray;
        } else if (originValue instanceof JSONObject) {
            return json((JSONObject) originValue);
        } else if (originValue instanceof Long) {
            return originValue;
        } else if (originValue instanceof Integer) {
            return originValue;
        } else {
            return originValue;
        }
    }

    public static JSONObject json(JSONObject json) {
        if (json == null) {
            throw new FlyMockException("json template is null!");
        }
        //初始化
        JSONObject result = new JSONObject();
        //循环
        for (Map.Entry<String, Object> entry : json.entrySet()) {
            String key = entry.getKey();
            KeyWrapper keyWrapper = new KeyWrapper(key);
            ValueWrapper valueWrapper = new ValueWrapper(entry.getValue());
            Node<String, Object> node = Strategy.keyStrategy(keyWrapper, valueWrapper);
            result.put(node.getKey(), node.getValue());
        }
        return result;
    }

}
