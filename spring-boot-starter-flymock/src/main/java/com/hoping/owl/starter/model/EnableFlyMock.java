package com.hoping.owl.starter.model;

import com.hoping.owl.starter.FlyMockAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by houping wang on 2019/4/23
 * 开启FlyMock，应用在类上
 * @author houping wang
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(FlyMockAutoConfiguration.class)
public @interface EnableFlyMock {
}
