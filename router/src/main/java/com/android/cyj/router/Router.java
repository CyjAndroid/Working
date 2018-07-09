package com.android.cyj.router;

import android.app.Application;
import android.text.TextUtils;

import java.util.List;

/**
 * Created by liuzhao on 2017/9/29.
 * EasyRouter的配置类
 */

public class Router {
    private static String injectSuffix = "_AutoAssign";
    private static volatile Router mRouter;
    public static Application mApplication;
    public static boolean isInited;

    private Router() {
    }

    public static Router getInstance() {
        if (mRouter == null) {
            synchronized (Router.class) {
                if (mRouter == null) {
                    mRouter = new Router();
                }
            }
        }
        return mRouter;
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
                    Class moduleInterceptor = Class.forName("com.android.cyj.router.AutoCreateModuleInterceptor_" + string);
                    if (moduleInterceptor != null) {
                        List list = (List) moduleInterceptor.getMethod("initModuleInterceptor").invoke(null);
                        Dispatcher.getActivityDispatcher().initInterceptors(list);
                    }
                }
                isInited = true;
            } catch (Exception e) {
//                EasyRouterLogUtils.e(e);
            }
        }
    }

    public <T extends IBaseModuleService> T getModuleService(Class<T> tClass) {
        return ModuleServiceManager.getModuleService(tClass);
    }

    public void inject(Object object) {
        if (object == null) {
            return;
        }
        String objectCName = object.getClass().getName();
        String injectCName = objectCName + injectSuffix;
        try {
            Class.forName(injectCName).getMethod("autoAssign", Object.class).invoke(null, object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RouterBuild build(String url) {
        RouterBuild build = new RouterBuild(mApplication, url);
        return build;
    }
}
