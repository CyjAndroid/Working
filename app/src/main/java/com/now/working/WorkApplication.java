package com.now.working;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

/**
 * Created by Cyj on 17/12/17.
 */
import com.android.annotation.DispatcherModules;
import com.android.cyj.router.RouterConfig;

@DispatcherModules({"app","plugin"})

public class WorkApplication extends Application {
    private static String TAG = WorkApplication.class.getSimpleName();
    private static WorkApplication mWorkApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mWorkApplication = this;

        RouterConfig.getInstance().init(this);
    }

    public static WorkApplication getApplication() {
        return mWorkApplication;
    }
}
