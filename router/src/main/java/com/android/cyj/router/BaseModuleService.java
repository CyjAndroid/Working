package com.android.cyj.router;

import android.content.Context;

/**
 * Created by chengyijun on 18/7/9.
 */

public interface BaseModuleService extends IBaseModuleService{
    interface ModuleInteractService extends BaseModuleService{
        void runModuleInteract(Context context);
    }
}
