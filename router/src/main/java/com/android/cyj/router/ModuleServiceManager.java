package com.android.cyj.router;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengyijun on 18/7/9.
 */

public class ModuleServiceManager {
    private static Map<Class<? extends IBaseModuleService>, IBaseModuleService> moduleInteracts = new HashMap<Class<? extends IBaseModuleService>, IBaseModuleService>();

    public static void register(Class<? extends IBaseModuleService> bClass, IBaseModuleService baseInteract) {
        moduleInteracts.put(bClass, baseInteract);
    }

    public static <T extends IBaseModuleService> T getModuleService(Class<T> tClass) {
        if (moduleInteracts.containsKey(tClass)) {
            return (T) moduleInteracts.get(tClass);
        }
        return null;
    }
}
