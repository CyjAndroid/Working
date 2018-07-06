package com.now.working;


import com.android.annotation.Interceptor;
import com.android.cyj.router.RouterBuild;

/**
 * Created by liuzhao on 2017/9/20.
 */

@Interceptor
public class ModuleTestInterceptor implements com.android.cyj.router.IInterceptor {

    @Override
    public boolean intercept(RouterBuild build) {
        return false;
    }

    @Override
    public void onIntercepted(RouterBuild build) {

    }
}
