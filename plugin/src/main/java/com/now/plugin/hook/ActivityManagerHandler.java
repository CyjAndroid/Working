package com.now.plugin.hook;

import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;

import com.now.plugin.PluginApplication;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static android.content.ContentValues.TAG;

/**
 * Created by Cyj on 18/1/30.
 */

public class ActivityManagerHandler implements InvocationHandler {

    private Object mBase;
    private String subName;
    public ActivityManagerHandler(Object base,String name){
        mBase = base;
        subName = name;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {

        if ("startActivity".equals(method.getName())) {
            // 只拦截这个方法
            // 替换参数, 任你所为;甚至替换原始Activity启动别的Activity偷梁换柱
            // API 23:
            // public final Activity startActivityNow(Activity parent, String id,
            // Intent intent, ActivityInfo activityInfo, IBinder token, Bundle state,
            // Activity.NonConfigurationInstances lastNonConfigurationInstances) {

            // 找到参数里面的第一个Intent 对象

            Intent raw;
            int index = 0;

            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Intent) {
                    index = i;
                    break;
                }
            }
            raw = (Intent) args[index];

            Intent newIntent = new Intent();

            // 替身Activity的包名, 也就是我们自己的"包名", Application Id, 如果用gradle打包
            String stubPackage = PluginApplication.getContext().getPackageName();

            // 这里我们把启动的Activity临时替换为 StubActivity
            ComponentName componentName = new ComponentName(stubPackage,
                    subName);
            newIntent.setComponent(componentName);

            // 把我们原始要启动的TargetActivity先存起来
            newIntent.putExtra("plugin", raw);

            // 替换掉Intent, 达到欺骗AMS的目的
            args[index] = newIntent;


            Log.d(TAG, "hook success");
            return method.invoke(mBase, args);

        }

        return method.invoke(mBase, args);
    }
}
