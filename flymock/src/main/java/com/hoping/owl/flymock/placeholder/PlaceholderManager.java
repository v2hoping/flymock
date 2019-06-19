package com.hoping.owl.flymock.placeholder;

/**
 * Created by houping wang on 2019/4/10
 * 占位处理器管理接口
 *
 * @author houping wang
 */
public interface PlaceholderManager {

    /**
     * 实现如何初始化该占位符处理管理器
     */
    void init();

    /**
     * 根据占位符key获得占位符处理对象
     *
     * @param key 占位符key
     * @return PlaceholderHandle
     */
    PlaceholderHandle select(String key);

    /**
     * 添加新的占位符处理器
     * @param placeholderHandle placeholderHandle
     * @return true/false
     */
    boolean put(PlaceholderHandle placeholderHandle);
}
