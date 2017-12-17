package com.now.working.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.now.working.WorkApplication;

/**
 * Created by Cyj on 17/12/17.
 */

public class SharedPreferencesUtils {
    private static SharedPreferencesUtils mInstance = null;
    private SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(
            WorkApplication.getApplication());

    private SharedPreferencesUtils() {

    }

    public static SharedPreferencesUtils getInstance() {
        if (mInstance == null) {
            synchronized (SharedPreferencesUtils.class) {
                if (mInstance == null) {
                    mInstance = new SharedPreferencesUtils();
                }
            }
        }
        return mInstance;
    }
}
