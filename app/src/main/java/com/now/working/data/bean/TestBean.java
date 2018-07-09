package com.now.working.data.bean;

import java.io.Serializable;

/**
 * Created by chengyijun on 18/7/9.
 */

public class TestBean implements Serializable {
    private String value = "a";

    @Override
    public String toString() {
        return "TestBean{" +
                "value='" + value + '\'' +
                '}';
    }
}
