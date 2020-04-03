package com.hoping.owl.flymock.annotation;

import java.lang.annotation.*;

/**
 * Created by houping wang on 2019/4/15
 *
 * @author houping wang
 */
@Target({ElementType.FIELD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface FiledMock {

    String strategy() default "";

    /**
     * 支持各种占位符
     * 例如："@string()"
     * @return 占位符，字符串
     */
    String value() default "";

}
