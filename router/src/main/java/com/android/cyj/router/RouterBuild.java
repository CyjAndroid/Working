package com.android.cyj.router;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by chengyijun on 18/7/5.
 */

public class RouterBuild {
    private Bundle mBundle;
    private String mUrl;
    private Application mApplication;

    public RouterBuild(Application application, String url) {
        mApplication = application;
        mBundle = new Bundle();
        setUrl(url);
    }

    public void navigation() {
        if (!TextUtils.isEmpty(mUrl)) {
            Dispatcher.getActivityDispatcher().open(mApplication, mUrl,mBundle);
        }
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }

    public RouterBuild withString(@Nullable String key, @Nullable String value) {
        mBundle.putString(key, value);
        return this;
    }

    public RouterBuild withBoolean(@Nullable String key, boolean value) {
        mBundle.putBoolean(key, value);
        return this;
    }

    public RouterBuild withInt(@Nullable String key, int value) {
        mBundle.putInt(key, value);
        return this;
    }

    public RouterBuild withFloat(@Nullable String key, float value) {
        mBundle.putFloat(key, value);
        return this;
    }
}
