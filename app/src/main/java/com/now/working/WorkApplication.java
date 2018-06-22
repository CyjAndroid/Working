package com.now.working;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

/**
 * Created by Cyj on 17/12/17.
 */

public class WorkApplication extends Application {
    private static String TAG = WorkApplication.class.getSimpleName();
    private static WorkApplication mWorkApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mWorkApplication = this;
    }

    public static WorkApplication getApplication() {
        return mWorkApplication;
    }
}
