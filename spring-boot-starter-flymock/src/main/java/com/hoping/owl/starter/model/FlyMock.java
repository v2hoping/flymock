package com.hoping.owl.starter.model;

import java.lang.annotation.*;

/**
 * Created by houping wang on 2019/4/23
 *
 * @author houping wang
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface FlyMock {

    String value() default "";

    MatchingMode matchingMode() default MatchingMode.ALL;

}
