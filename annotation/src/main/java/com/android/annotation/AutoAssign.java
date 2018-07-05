package com.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by chengyijun on 18/7/4.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
public @interface AutoAssign {
    String name() default "";
}
