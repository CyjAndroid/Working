package com.android.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by chengyijun on 18/6/29.
 */

@Retention(RetentionPolicy.CLASS)
public @interface DispatcherModules {
    String[] value();
}
