package com.hoping.owl.flymock.cache;

/**
 * Created by houping wang on 2019/3/15
 *
 * @author houping wang
 */
public class CacheBean<T> {

    private final T value;

    private final long expirMillisecond;

    private long millisecond;

    public CacheBean(T value, long expirMillisecond) {
        this.value = value;
        this.expirMillisecond = expirMillisecond;
        refense();
    }

    public T getCacheValue() {
        return value;
    }

    public long getExpirMillisecond() {
        return expirMillisecond;
    }

    public T getAliveCacheValue() {
        return isExpire() ? null : value;
    }

    public boolean isExpire() {
        return millisecond < System.currentTimeMillis();
    }

    public void refense() {
        this.millisecond = System.currentTimeMillis() + expirMillisecond;
    }

}
