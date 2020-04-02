package com.hoping.owl.flymock.rule;

import com.hoping.owl.flymock.util.StringUtil;

import java.util.Map;
import java.util.Objects;

/**
 * Created by houping wang on 2020/4/2
 *
 * @author houping wang
 */
public class RuleUnit {

    public RuleUnit(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public RuleUnit(Map.Entry<String, Object> entry) {
        this.key = StringUtil.lastSpotKey(entry.getKey());
        this.value = entry.getValue().toString();
    }

    public RuleUnit() {
    }

    private String key;

    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RuleUnit ruleUnit = (RuleUnit) o;
        return Objects.equals(key, ruleUnit.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
