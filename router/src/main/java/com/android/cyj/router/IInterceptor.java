package com.android.cyj.router;


/**
 * Created by liuzhao on 2017/9/18.
 */

public interface IInterceptor {

    boolean intercept(RouterBuild build);

    void onIntercepted(RouterBuild build);
}
