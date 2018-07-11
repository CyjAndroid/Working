package com.now.working.ui.service;

import android.util.Log;

import com.android.annotation.Dispatcher;
import com.android.cyj.router.DegradeService;

/**
 * Created by chengyijun on 18/7/10.
 */
@Dispatcher("cyj://plugin_degrade_service")
public class PluginDegradeService implements DegradeService {
    @Override
    public void doLost() {
        Log.e("degrad","do lost");
//        Toast.makeText(PluginApplication.getContext(),"plugin DegradeService",Toast.LENGTH_SHORT).show();
    }
}
