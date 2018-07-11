package com.now.working;

import android.app.Application;

/**
 * Created by Cyj on 17/12/17.
 */
import com.android.annotation.DispatcherModules;
import com.android.cyj.router.Router;
import com.now.working.ui.service.PluginDegradeService;

@DispatcherModules({"app","plugin"})

public class WorkApplication extends Application {
    private static String TAG = WorkApplication.class.getSimpleName();
    private static WorkApplication mWorkApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mWorkApplication = this;

        Router.getInstance().init(this);
        Router.getInstance().setGlobalDegradeService(PluginDegradeService.class);
    }

    public static WorkApplication getApplication() {
        return mWorkApplication;
    }
}
