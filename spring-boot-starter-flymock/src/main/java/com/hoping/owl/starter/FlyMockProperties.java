package com.hoping.owl.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by houping wang on 2019/4/23
 *
 * @author houping wang
 */
@ConfigurationProperties(prefix = "spring.fly.mock")
public class FlyMockProperties {

    /**
     * 是否开启拦截mock
     */
    private Boolean enable;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
