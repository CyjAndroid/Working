package com.now.plugin;

import android.app.Application;
import android.content.Context;

/**
 * Created by Cyj on 18/1/28.
 */

public class PluginApplication extends Application {
    private static boolean isHookPMS = false;

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }

    public static boolean isHookPMS() {
        return isHookPMS;
    }

    public static void setHookPMS(boolean hookPMS) {
        isHookPMS = hookPMS;
    }
}
