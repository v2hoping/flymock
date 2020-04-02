package com.hoping.owl.flymock.rule;

import com.hoping.owl.flymock.util.StringUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by houping wang on 2020/3/31
 * 以及全局生成规则、指定生成规则
 * 注解>指定类配置>默认配置(精准)>默认匹配(模糊)
 * @author houping wang
 */
public class Rule {

    /**
     * flymock.common.contain前缀,模糊匹配Key
     * 例如
     * flymock.common.contain.name=@cname()
     * flymock.common.contain.age=@integer(0,100)
     */
    private final Map<String, String> containRules = new ConcurrentHashMap<>();

    /**
     * flymock.common.equal
     * 例如
     * flymock.common.equal.name=@cname()
     */
    private final Map<String, String> equalRules = new ConcurrentHashMap<>();

    /**
     * 是否开启默认规则，默认true
     * flymock.common.flag=true
     */
    private boolean flag = true;

    public Rule(boolean flag) {
        this.flag = flag;
    }

    public Rule() {
    }

    public String getPlaceholder(String field) {
        String lowerField = field.toLowerCase();
        String value = equalRules.get(lowerField);
        if(StringUtil.isNotBlank(value)) {
            return value;
        }
        Set<Map.Entry<String, String>> entries = containRules.entrySet();
        for(Map.Entry<String,String> entry : entries) {
            if(lowerField.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public String putContainRule(String key ,String value) {
        return containRules.put(key.toLowerCase(), value);
    }

    public String putEqualRule(String key, String value) {
        return equalRules.put(key.toLowerCase(), value);
    }

    public void putContainRules(Set<RuleUnit> ruleUnits) {
        for(RuleUnit ruleUnit : ruleUnits) {
            this.putContainRule(ruleUnit.getKey(), ruleUnit.getValue());
        }
    }

    public void putEqualRules(Set<RuleUnit> ruleUnits) {
        for(RuleUnit ruleUnit : ruleUnits) {
            this.putEqualRule(ruleUnit.getKey(), ruleUnit.getValue());
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
