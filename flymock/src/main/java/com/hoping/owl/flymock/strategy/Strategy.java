package com.hoping.owl.flymock.strategy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hoping.owl.flymock.FlyMockException;
import com.hoping.owl.flymock.util.StringUtil;
import com.hoping.owl.flymock.FlyRandom;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

/**
 * Created by houping wang on 2019/4/4
 *
 * @author houping wang
 */
public class Strategy {

    public static Node<String, Object> keyStrategy(KeyWrapper keyWrapper, ValueWrapper valueWrapper) {
        //validate
        if (keyWrapper == null) {
            throw new FlyMockException("keyWrapper不能为空");
        }
        if (StringUtil.isBlank(keyWrapper.getKey())) {
            throw new FlyMockException("key必须存在，不能为空");
        }
        ValueStrategy valueStrategy = ValueStrategyFactory.createResolver(keyWrapper.getStrategyWrapper());
        Object resolverValue = valueStrategy.resolver(valueWrapper);
        return new Node<>(keyWrapper.getKey(), resolverValue);
    }

    /**
     * 值策略工厂.
     */
    static class ValueStrategyFactory {

        /**
         * 根据策略字段，创建策略执行者
         *
         * @param strategyWrapper 策略字段
         *                        'name|min-max': value
         *                        'name|count': value
         *                        'name|min-max.dmin-dmax': value
         *                        'name|min-max.dcount': value
         *                        'name|count.dmin-dmax': value
         *                        'name|count.dcount': value
         *                        'name|+step': value
         * @return ValueStrategy 处理值的执行者
         */
        public static ValueStrategy createResolver(StrategyWrapper strategyWrapper) {
            StrategyType strategyType = strategyWrapper.getStrategyType();
            switch (strategyType) {
                case NONE:
                    return new None();
                case STEP:
                    return new AddStep(strategyWrapper.getStep());
                case COUNT:
                    return new Count(strategyWrapper.getCount());
                case MIN_MAX:
                    return new MinMax(strategyWrapper.getMin(), strategyWrapper.getMax());
                case COUNT_DCOUNT:
                    return new CountDcount(strategyWrapper.getCount(), strategyWrapper.getDcount());
                case MIN_MAX_DCOUNT:
                    return new MinMaxDcount(strategyWrapper.getMin(), strategyWrapper.getMax(), strategyWrapper.getDcount());
                case COUNT_DMIN_DMAX:
                    return new CountDminDmax(strategyWrapper.getCount(), strategyWrapper.getDmin(), strategyWrapper.getDmax());
                case MIN_MAX_DMIN_DMAX:
                    return new MinMaxDminDmax(strategyWrapper.getMin(), strategyWrapper.getMax(),
                            strategyWrapper.getDmin(), strategyWrapper.getDmax());
                default:
                    throw new FlyMockException("未知strategyType类型错误！");
            }
        }

    }

    /**
     * +step策略
     */
    static class AddStep extends AbstractValueStrategy {

        private Integer step;

        private AddStep(Integer step) {
            this.step = step;
        }

        @Override
        Object resolveDependStrategy(ValueWrapper generateWrapper) {
            if (generateWrapper.belongNumber()) {
                BigDecimal bigDecimal = (BigDecimal) generateWrapper.getOriginValue();
                return bigDecimal.add(new BigDecimal(step));
            }
            if (generateWrapper.belongInteger()) {
                return (Integer) generateWrapper.getOriginValue() + step;
            }
            if (generateWrapper.belongLong()) {
                return (Long) generateWrapper.getOriginValue() + step;
            }
            if (generateWrapper.belongArray()) {
                JSONArray array = (JSONArray) generateWrapper.getOriginValue();
                JSONArray newArray = new JSONArray();
                if (array.size() < step) {
                    newArray.add(array.get(array.size() - 1));
                }
                if (step > 0 && step <= array.size()) {
                    newArray.add(array.get(step - 1));
                }
                return newArray;
            }
            return generateWrapper.getOriginValue();
        }

    }

    static class Count extends AbstractValueStrategy {

        private Integer count;

        private Count(Integer count) {
            this.count = count;
        }

        @Override
        Object resolveDependStrategy(ValueWrapper generateWrapper) {
            if (generateWrapper.belongString()) {
                StringBuilder sb = new StringBuilder();
                for (int i = count; i > 0; i--) {
                    sb.append(generateWrapper.getOriginValue());
                }
                return sb.toString();
            }
            if (generateWrapper.belongObject()) {
                JSONObject object = (JSONObject) generateWrapper.getOriginValue();
                Set<Map.Entry<String, Object>> entries = object.entrySet();
                JSONObject newObject = new JSONObject();
                int i = 0;
                for (Map.Entry<String, Object> entry : entries) {
                    if (i++ >= count) {
                        return newObject;
                    }
                    newObject.put(entry.getKey(), entry.getValue());
                }
                return newObject;
            }
            if (generateWrapper.belongArray()) {
                JSONArray array = (JSONArray) generateWrapper.getOriginValue();
                if (count == 1) {
                    return StrategyUtil.random(array);
                }
                JSONArray newJsonArray = new JSONArray(array.size() * count);
                for (int i = 0; i < count; i++) {
                    newJsonArray.addAll(array);
                }
                return newJsonArray;
            }
            if (generateWrapper.belongBoolean() && count == 1) {
                return FlyRandom.booleanRandom(1, 1, (Boolean) generateWrapper.getOriginValue());
            }
            return generateWrapper.getOriginValue();
        }
    }

    static class None extends AbstractValueStrategy {
        @Override
        Object resolveDependStrategy(ValueWrapper generateWrapper) {
            return generateWrapper.getOriginValue();
        }
    }

    static class MinMax extends AbstractValueStrategy {

        private Integer min;

        private Integer max;

        private MinMax(Integer min, Integer max) {
            this.min = min;
            this.max = max;
        }

        @Override
        Object resolveDependStrategy(ValueWrapper generateWrapper) {
            if (generateWrapper.belongString()) {
                StringBuilder sb = new StringBuilder();
                Integer maxValue = FlyRandom.natural(min, max);
                for (int i = 0; i < maxValue; i++) {
                    sb.append(generateWrapper.getOriginValue());
                }
                return sb.toString();
            }
            if (generateWrapper.belongNumber()) {
                return new BigDecimal(FlyRandom.natural(min, max));
            }
            if (generateWrapper.belongInteger()) {
                return FlyRandom.natural(min, max);
            }
            if (generateWrapper.belongLong()) {
                Integer value = FlyRandom.natural(min, max);
                return Long.parseLong(value.toString());
            }
            if (generateWrapper.belongBoolean()) {
                return FlyRandom.booleanRandom(min, max, (Boolean) generateWrapper.getOriginValue());
            }
            if (generateWrapper.belongArray()) {
                return StrategyUtil.repeat((JSONArray) generateWrapper.getOriginValue(), min, max);
            }
            if (generateWrapper.belongObject()) {
                JSONObject object = (JSONObject) generateWrapper.getOriginValue();
                Set<Map.Entry<String, Object>> entries = object.entrySet();
                JSONObject newObject = new JSONObject();
                int i = 0;
                Integer maxInteger = FlyRandom.natural(min, max);
                for (Map.Entry<String, Object> entry : entries) {
                    if (i++ >= maxInteger) {
                        return newObject;
                    }
                    newObject.put(entry.getKey(), entry.getValue());
                }
                return newObject;
            }
            return generateWrapper.getOriginValue();
        }
    }

    static class MinMaxDminDmax extends AbstractValueStrategy {

        private Integer min;

        private Integer max;

        private Integer dmin;

        private Integer dMax;

        private MinMaxDminDmax(Integer min, Integer max, Integer dmin, Integer dMax) {
            this.min = min;
            this.max = max;
            this.dmin = dmin;
            this.dMax = dMax;
        }

        @Override
        Object resolveDependStrategy(ValueWrapper generateWrapper) {
            if (generateWrapper.belongNumber()) {
                return new BigDecimal(FlyRandom.doubleRandom(min, max, dmin, dMax));
            }
            return generateWrapper.getOriginValue();
        }
    }

    static class MinMaxDcount extends AbstractValueStrategy {

        private Integer min;

        private Integer max;

        private Integer dcount;

        public MinMaxDcount(Integer min, Integer max, Integer dcount) {
            this.min = min;
            this.max = max;
            this.dcount = dcount;
        }

        @Override
        Object resolveDependStrategy(ValueWrapper generateWrapper) {
            if (generateWrapper.belongNumber()) {
                return new BigDecimal(FlyRandom.doubleRandom(min, max, dcount, dcount));
            }
            return generateWrapper.getOriginValue();
        }
    }

    static class CountDminDmax extends AbstractValueStrategy {

        private Integer count;

        private Integer dmin;

        private Integer dmax;

        private CountDminDmax(Integer count, Integer dmin, Integer dmax) {
            this.count = count;
            this.dmin = dmin;
            this.dmax = dmax;
        }

        @Override
        Object resolveDependStrategy(ValueWrapper generateWrapper) {
            if (generateWrapper.belongNumber()) {
                return new BigDecimal(FlyRandom.doubleRandom(count, count, dmin, dmax));
            }
            return generateWrapper.getOriginValue();
        }
    }

    static class CountDcount extends AbstractValueStrategy {

        private Integer count;

        private Integer dcount;

        private CountDcount(Integer count, Integer dcount) {
            this.count = count;
            this.dcount = dcount;
        }

        @Override
        Object resolveDependStrategy(ValueWrapper generateWrapper) {
            if (generateWrapper.belongNumber()) {
                return new BigDecimal(FlyRandom.doubleRandom(count, count, dcount, dcount));
            }
            return generateWrapper.getOriginValue();
        }
    }

    static abstract class AbstractValueStrategy implements ValueStrategy {

        abstract Object resolveDependStrategy(ValueWrapper value);

        private AbstractValueStrategy() {
        }

        @Override
        public Object resolver(ValueWrapper valueWrapper) {
            Object preValue = ValueResolver.resolver(valueWrapper);
            return resolveDependStrategy(new ValueWrapper(preValue));
        }
    }

    interface ValueStrategy {
        /**
         * 依据策略解析
         *
         * @param valueWrapper 值包装类
         * @return 值
         */
        Object resolver(ValueWrapper valueWrapper);
    }

}
