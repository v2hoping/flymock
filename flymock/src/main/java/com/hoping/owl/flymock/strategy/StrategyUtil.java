package com.hoping.owl.flymock.strategy;

import com.alibaba.fastjson.JSONArray;
import com.hoping.owl.flymock.FlyMockException;
import com.hoping.owl.flymock.FlyRandom;
import com.hoping.owl.flymock.constants.Constants;
import com.hoping.owl.flymock.util.StringUtil;

/**
 * Created by houping wang on 2019/4/8
 *
 * @author houping wang
 */
public class StrategyUtil {

    public static JSONArray repeat(JSONArray array, Integer count) {
        JSONArray newJsonArray = new JSONArray(array.size() * count);
        for (int i = 0; i < count; i++) {
            newJsonArray.addAll(array);
        }
        return newJsonArray;
    }

    public static JSONArray repeat(JSONArray array, Integer min, Integer max) {
        Integer count = FlyRandom.natural(min, max);
        JSONArray newJsonArray = new JSONArray(array.size() * count);
        for (int i = 0; i < count; i++) {
            newJsonArray.addAll(array);
        }
        return newJsonArray;
    }

    public static JSONArray random(JSONArray array) {
        JSONArray newJsonArray = new JSONArray(1);
        Integer natural = FlyRandom.natural(0, array.size() - 1);
        newJsonArray.add(array.get(natural));
        return newJsonArray;
    }

    public static String addKeyStrategy(String originalKey, String strategy) {
        //已经包含策略则返回原值
        if(originalKey.contains(Constants.SEPARATOR)) {
            return originalKey;
        }
        try{
            StrategyType strategyTypeEnum = getStrategyTypeEnum(strategy);
            if(strategyTypeEnum == StrategyType.NONE) {
                return originalKey;
            }else{
                return originalKey + "|" + strategy;
            }
        }catch (FlyMockException ex) {
            return originalKey;
        }
    }

    public static StrategyType getStrategyTypeEnum(String strategy) {
        return new StrategyWrapper(strategy).getStrategyType();
    }
}
