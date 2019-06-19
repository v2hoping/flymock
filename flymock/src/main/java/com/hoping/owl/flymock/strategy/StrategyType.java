package com.hoping.owl.flymock.strategy;

/**
 * Created by houping wang on 2019/4/8
 *
 * @author houping wang
 */
public enum StrategyType {

    /**
     * 'name|min-max': value
     * 'name|count': value
     * 'name|min-max.dmin-dmax': value
     * 'name|min-max.dcount': value
     * 'name|count.dmin-dmax': value
     * 'name|count.dcount': value
     * 'name|+step': value
     */
    MIN_MAX,
    COUNT,
    MIN_MAX_DMIN_DMAX,
    MIN_MAX_DCOUNT,
    COUNT_DMIN_DMAX,
    COUNT_DCOUNT,
    STEP,
    NONE;
}
