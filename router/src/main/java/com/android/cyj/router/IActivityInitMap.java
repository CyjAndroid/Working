package com.android.cyj.router;

import com.android.annotation.model.RouteMeta;

import java.util.HashMap;

/**
 * Created by chengyijun on 18/6/30.
 */

public interface IActivityInitMap {
    void initActivityMap(HashMap<String, RouteMeta> activityMap);
}
