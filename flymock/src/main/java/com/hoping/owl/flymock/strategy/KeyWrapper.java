package com.hoping.owl.flymock.strategy;

import com.hoping.owl.flymock.constants.Constants;

/**
 * Created by houping wang on 2019/4/4
 *
 * @author houping wang
 */
public class KeyWrapper {

    private static final String SEPARATOR = Constants.SEPARATOR;

    private String key;

    private String keyStrategy;

    private StrategyWrapper strategyWrapper;

    public KeyWrapper(String key, String strategy) {
        this.key = key;
        this.strategyWrapper = new StrategyWrapper(strategy);
        this.keyStrategy = key + SEPARATOR + strategy;
    }

    public KeyWrapper(String keyStrategy) {
        this.keyStrategy = keyStrategy;
        String[] strings = keyStrategy.split(SEPARATOR);
        this.key = strings[0];
        if (strings.length > 1) {
            this.strategyWrapper = new StrategyWrapper(strings[1]);
        } else {
            this.strategyWrapper = new StrategyWrapper(null);
        }
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKeyStrategy() {
        return keyStrategy;
    }

    public StrategyWrapper getStrategyWrapper() {
        return strategyWrapper;
    }
}
