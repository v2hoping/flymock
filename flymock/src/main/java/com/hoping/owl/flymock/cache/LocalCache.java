package com.hoping.owl.flymock.cache;

import com.hoping.owl.flymock.FlyMockException;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by houping wang on 2019/3/15
 *
 * @author houping wang
 */
public class LocalCache<T> {

    private volatile CacheBean<T> cacheBean = null;

    private ScheduledExecutorService swapExpiredPool;

    public LocalCache() {
        //thread
        swapExpiredPool = new ScheduledThreadPoolExecutor(1);
        swapExpiredPool.scheduleWithFixedDelay(new SwapExpiredNodeWork(), 5, 30, TimeUnit.SECONDS);
    }

    private class SwapExpiredNodeWork implements Runnable {
        @Override
        public void run() {
            if (cacheBean == null || !cacheBean.isExpire()) {
                return;
            }
            cacheBean = null;
        }
    }

    public T get() {
        //保存此刻对象
        CacheBean<T> currentBean = cacheBean;
        if (null == currentBean) {
            return null;
        }
        T value = currentBean.getCacheValue();
        if (null == value) {
            return null;
        }
        //超时不需要重新获取，直接刷新超时时间即可
        currentBean.refense();
        return value;
    }

    public void put(T value, long expireSecond) {
        if (null == value) {
            throw new FlyMockException("LocalCache param value is null, please set value");
        }
        if (expireSecond < 0) {
            throw new FlyMockException("LocalCache param expireSecond least 1 second");
        }
        cacheBean = new CacheBean<>(value, expireSecond * 1000);
    }
}
