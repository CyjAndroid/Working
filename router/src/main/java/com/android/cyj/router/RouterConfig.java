package com.android.cyj.router;

import android.app.Application;
import android.content.Intent;
import android.text.TextUtils;

import java.util.List;

/**
 * Created by liuzhao on 2017/9/29.
 * EasyRouter的配置类
 */

public class RouterConfig {
    private static volatile RouterConfig mRouterConfig;
    public static Application mApplication;
    public static boolean isInited;

    private RouterConfig() {
    }

    public static RouterConfig getInstance() {
        if (mRouterConfig == null) {
            synchronized (RouterConfig.class) {
                if (mRouterConfig == null) {
                    mRouterConfig = new RouterConfig();
                }
            }
        }
        return mRouterConfig;
    }

    public void init(Application application) {
        if (!isInited) {
            mApplication = application;
            try {
                // deal dispatcher and service
                Class routerInit = Class.forName("com.android.cyj.router.RouterInit");
                if (routerInit != null) {
                    routerInit.getMethod("init").invoke(null);
                }
                for (String string : Dispatcher.mModuleNames) {
                    // deal interceptor
//                    Class moduleInterceptor = Class.forName("com.android.cyj.router.AutoCreateModuleInterceptor_" + string);
//                    if (moduleInterceptor != null) {
//                        List list = (List) moduleInterceptor.getMethod("initModuleInterceptor").invoke(null);
//                        ActivityDispatcher.getActivityDispatcher().initInterceptors(list);
//                    }
                }
                isInited = true;
            } catch (Exception e) {
//                EasyRouterLogUtils.e(e);
            }
        }
    }


//    public RouterConfig setScheme(String scheme) {
//        Dispatcher.getActivityDispatcher().setScheme(scheme);
//        return this;
//    }

    public static void open(String url){
        if(!TextUtils.isEmpty(url)){
            Dispatcher.getActivityDispatcher().open(mApplication,url);
        }
    }

}
