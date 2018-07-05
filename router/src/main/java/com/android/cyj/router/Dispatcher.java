package com.android.cyj.router;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by chengyijun on 18/6/30.
 */

public class Dispatcher {
    private static Dispatcher mActivityDispatcher;
    private HashMap<String, Class> mRealActivityMaps = new HashMap<String, Class>();// store the mapping of strings and class
    public static List<String> mModuleNames = new ArrayList<String>();// store module names


    public static Dispatcher getActivityDispatcher() {
        if (mActivityDispatcher == null) {
            synchronized (Dispatcher.class) {
                if (mActivityDispatcher == null) {
                    mActivityDispatcher = new Dispatcher();
//                    sDefaultRouterCallBack = new DefaultRouterCallBack();
                }
            }
        }
        return mActivityDispatcher;
    }

    public void initActivityMaps(IActivityInitMap activityInitMap) {
        if (activityInitMap == null) {
            return;
        }
        activityInitMap.initActivityMap(mRealActivityMaps);
        String name = activityInitMap.getClass().getSimpleName();
        if (!TextUtils.isEmpty(name) && name.contains("_")) {
            String splits[] = name.split("_");
            if (splits != null && splits.length > 1 && !mModuleNames.contains(splits[1])) {
                mModuleNames.add(splits[1]);
            }
        }
    }

    public Class getTargetClass(String targetUrl) {
        Uri targetUri = Uri.parse(targetUrl);
        String targetHost = targetUri.getHost();
        int pathSegmentSize = targetUri.getPathSegments().size();

        Uri currentUri = null;
        String currentHost = null;
        int currentPathSegmentSize = 0;

        //此处scheme已经校验；放心使用。
        for (String currentCheckUrl : mRealActivityMaps.keySet()) {
            currentUri = Uri.parse(currentCheckUrl);
            currentHost = currentUri.getHost();
            currentPathSegmentSize = currentUri.getPathSegments().size();
            if (TextUtils.equals(currentHost, targetHost) && pathSegmentSize == currentPathSegmentSize) {
                //may optimize
                if (pathSegmentSize > 0 && currentPathSegmentSize > 0
                        && !TextUtils.equals(currentUri.getPathSegments().get(0), targetUri.getPathSegments().get(0))) {
                    break;
                }
                return mRealActivityMaps.get(currentCheckUrl);
            }
        }
        return null;
    }

    public void open(Application mApplication, String url,Bundle bundle) {
        Intent intent = new Intent(mApplication, getTargetClass(url));
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mApplication.startActivity(intent);
    }
}
