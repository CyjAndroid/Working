package com.now.plugin.hook;

import android.content.pm.PackageInfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Cyj on 18/1/28.
 */

public class PMSHookHandler implements InvocationHandler {
    private final String GET_PACKAGE_INFO = "getPackageInfo";
    private Object mBase;

    public PMSHookHandler(Object base) {
        mBase = base;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if(GET_PACKAGE_INFO.equals(method.getName())){
            return new PackageInfo();
        }
        return method.invoke(mBase,objects);
    }
}
