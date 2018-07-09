package com.now.plugin.service;

import android.content.Context;
import android.widget.Toast;

import com.android.annotation.ModuleService;
import com.android.cyj.router.BaseModuleService;

/**
 * Created by chengyijun on 18/7/9.
 */
@ModuleService
public class ModuleInteractService implements BaseModuleService.ModuleInteractService {
    @Override
    public void runModuleInteract(Context context) {
        Toast.makeText(context,"this is plugin service!!!",Toast.LENGTH_SHORT).show();
    }
}
