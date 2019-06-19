package com.hoping.owl.flymock.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;

/**
 * Created by houping wang on 2019/4/4
 *
 * @author houping wang
 */
public class ValueWrapper {

    private Object originValue;

    public ValueWrapper(Object originValue) {
        this.originValue = originValue;
    }

    public Object getOriginValue() {
        return originValue;
    }

    public void setOriginValue(Object originValue) {
        this.originValue = originValue;
    }

    public boolean belongString() {
        return originValue instanceof String;
    }

    public boolean belongNumber() {
        return originValue instanceof BigDecimal;
    }

    public boolean belongBoolean() {
        return originValue instanceof Boolean;
    }

    public boolean belongObject() {
        return originValue instanceof JSONObject;
    }

    public boolean belongArray() {
        return originValue instanceof JSONArray;
    }

    public boolean belongLong() {
        return originValue instanceof Long;
    }

    public boolean belongInteger() {
        return originValue instanceof Integer;
    }

    @Override
    public String toString() {
        return "ValueWrapper{" +
                "originValue=" + originValue +
                '}';
    }
}
