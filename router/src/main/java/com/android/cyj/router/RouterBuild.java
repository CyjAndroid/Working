package com.android.cyj.router;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengyijun on 18/7/5.
 */

public class RouterBuild {
    private Bundle mBundle;
    private String mUrl;
    private Application mApplication;
    public List<IInterceptor> mInterceptors = new ArrayList<>();


    public RouterBuild(Application application, String url) {
        mApplication = application;
        mBundle = new Bundle();
        setUrl(url);
    }

    public Object navigation() {
        if (!TextUtils.isEmpty(mUrl)) {
            return Dispatcher.getActivityDispatcher().open(mApplication, mUrl, this);
        }
        return null;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }

    public Bundle getBundle() {
        return mBundle;
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

    public RouterBuild withSerializable(@Nullable String key, Serializable serializable) {
        mBundle.putSerializable(key, serializable);
        return this;
    }

    public RouterBuild withParcelable(@Nullable String key, Parcelable parcelable) {
        mBundle.putParcelable(key, parcelable);
        return this;
    }

    public RouterBuild addInterceptor(IInterceptor interceptor) {
        if (interceptor != null) {
            mInterceptors.add(interceptor);
        }
        return this;
    }
}
