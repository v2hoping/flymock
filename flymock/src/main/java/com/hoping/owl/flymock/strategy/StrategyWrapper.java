package com.hoping.owl.flymock.strategy;

import com.hoping.owl.flymock.FlyMockException;
import com.hoping.owl.flymock.util.StringUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by houping wang on 2019/4/8
 *
 * @author houping wang
 */
public class StrategyWrapper {

    private static final String SEPATATOR_ADD = "+";
    private static final String SEPATATOR_COMMA = ".";
    private static final String SEPATATOR_BAR = "-";

    public StrategyWrapper(String strategyStr) {
        this.originStrategyStr = strategyStr;
        init();
    }

    private Integer min;

    private Integer max;

    private Integer count;

    private Integer dmin;

    private Integer dmax;

    private Integer dcount;

    private Integer step;

    /**
     * 策略类型
     */
    private StrategyType strategyType;

    /**
     * 原始策略字符串
     */
    private final String originStrategyStr;

    private void init() {
        try {
            if (StringUtil.isBlank(originStrategyStr)) {
                this.strategyType = StrategyType.NONE;
                return;
            }
            char[] chars = this.originStrategyStr.toCharArray();
            List<String> characters = new ArrayList<>();
            int sPoint = 0;
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '-' || chars[i] == '.' || chars[i] == '+') {
                    if (sPoint != i) {
                        characters.add(new String(chars, sPoint, (i - sPoint)));
                    }
                    characters.add(chars[i] + "");
                    sPoint = i + 1;
                }
                if (i == chars.length - 1 && chars.length != sPoint) {
                    characters.add(new String(chars, sPoint, (chars.length - sPoint)));
                }
            }
            //分类
            if (characters.size() == 1) {
                this.strategyType = StrategyType.COUNT;
                this.count = Integer.parseInt(characters.get(0));
                return;
            } else if (characters.size() == 2 && SEPATATOR_ADD.equals(characters.get(0))) {
                this.strategyType = StrategyType.STEP;
                this.step = Integer.parseInt(characters.get(1));
                return;
            } else if (characters.size() == 3) {
                if (SEPATATOR_BAR.equals(characters.get(1))) {
                    this.strategyType = StrategyType.MIN_MAX;
                    this.min = Integer.parseInt(characters.get(0));
                    this.max = Integer.parseInt(characters.get(2));
                    return;
                }
                if (SEPATATOR_COMMA.equals(characters.get(1))) {
                    this.strategyType = StrategyType.COUNT_DCOUNT;
                    this.count = Integer.parseInt(characters.get(0));
                    this.dcount = Integer.parseInt(characters.get(2));
                    return;
                }
            } else if (characters.size() == 5) {
                if (SEPATATOR_BAR.equals(characters.get(1)) && SEPATATOR_COMMA.equals(characters.get(3))) {
                    this.strategyType = StrategyType.MIN_MAX_DCOUNT;
                    this.min = Integer.parseInt(characters.get(0));
                    this.max = Integer.parseInt(characters.get(2));
                    this.dcount = Integer.parseInt(characters.get(4));
                    return;
                }
                if (SEPATATOR_COMMA.equals(characters.get(1))) {
                    this.strategyType = StrategyType.COUNT_DMIN_DMAX;
                    this.count = Integer.parseInt(characters.get(0));
                    this.dmin = Integer.parseInt(characters.get(2));
                    this.dmax = Integer.parseInt(characters.get(4));
                    return;
                }
            } else if (characters.size() == 7
                    && SEPATATOR_BAR.equals(characters.get(1))
                    && SEPATATOR_COMMA.equals(characters.get(3))
                    && SEPATATOR_BAR.equals(characters.get(5))) {
                this.strategyType = StrategyType.MIN_MAX_DMIN_DMAX;
                this.min = Integer.parseInt(characters.get(0));
                this.max = Integer.parseInt(characters.get(2));
                this.dmin = Integer.parseInt(characters.get(4));
                this.dmax = Integer.parseInt(characters.get(6));
                return;
            }
            throw new FlyMockException("解析策略字符串失败，格式错误.input string:" + originStrategyStr);
        } catch (NumberFormatException e) {
            throw new FlyMockException("解析策略字符串失败，必须使用数值.message:" + e.getMessage(), e);
        }
    }

    public Integer getMin() {
        return min;
    }

    public Integer getMax() {
        return max;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getDmin() {
        return dmin;
    }

    public Integer getDmax() {
        return dmax;
    }

    public Integer getDcount() {
        return dcount;
    }

    public Integer getStep() {
        return step;
    }

    public StrategyType getStrategyType() {
        return strategyType;
    }

    public String getOriginStrategyStr() {
        return originStrategyStr;
    }

    @Override
    public String toString() {
        return "StrategyWrapper{" +
                "min=" + min +
                ", max=" + max +
                ", count=" + count +
                ", dmin=" + dmin +
                ", dmax=" + dmax +
                ", dcount=" + dcount +
                ", step=" + step +
                ", strategyType=" + strategyType +
                ", originStrategyStr='" + originStrategyStr + '\'' +
                '}';
    }
}
