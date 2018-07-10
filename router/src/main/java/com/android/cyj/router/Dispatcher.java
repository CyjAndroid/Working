package com.android.cyj.router;

import android.app.Application;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.annotation.model.RouteMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chengyijun on 18/6/30.
 */

public class Dispatcher {
    private static Dispatcher mActivityDispatcher;
    private HashMap<String, RouteMeta> mRealActivityMaps = new HashMap<String, RouteMeta>();// store the mapping of strings and class
    public static List<String> mModuleNames = new ArrayList<String>();// store module names
    private static List<IInterceptor> mRealInterceptors = new ArrayList<IInterceptor>();// store the interceptor for UI Action


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

    public void initInterceptors(List<IInterceptor> interceptors) {
        if (interceptors == null) {
            return;
        }
        mRealInterceptors.addAll(interceptors);
    }


    public RouteMeta getTargetClass(String targetUrl) {
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

    public Object open(Application mApplication, String url, RouterBuild build) {

        List<IInterceptor> interceptors = new ArrayList<IInterceptor>();
        if (build.mInterceptors != null && !build.mInterceptors.isEmpty()) {
            interceptors = build.mInterceptors;
            for (IInterceptor iInterceptor : interceptors) {
                iInterceptor.onIntercepted(build);
            }
        }

        RouteMeta routeMeta = getTargetClass(url);
        if (RouteMeta.TYPE_ACTIVITY.equals(routeMeta.getClassType())) {
            Intent intent = new Intent(mApplication, routeMeta.getDestination());
            intent.putExtras(build.getBundle());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mApplication.startActivity(intent);
        } else if (RouteMeta.TYPE_FRAGMENT.equals(routeMeta.getClassType())
                || RouteMeta.TYPE_FRAGMENT_V4.equals(routeMeta.getClassType())) {
            Class fragmentMeta = routeMeta.getDestination();
            try {
                Object instance = fragmentMeta.getConstructor().newInstance();
//                if (instance instanceof Fragment) {
//                    ((Fragment) instance).setArguments(routeMeta.getExtras());
//                } else if (instance instanceof android.support.v4.app.Fragment) {
//                    ((android.support.v4.app.Fragment) instance).setArguments(postcard.getExtras());
//                }
                Toast.makeText(mApplication,"get Fragment!",Toast.LENGTH_SHORT).show();
                return instance;
            } catch (Exception ex) {
            }
        }

        return null;
    }
}
