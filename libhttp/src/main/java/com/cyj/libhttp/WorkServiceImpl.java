package com.cyj.libhttp;

import android.util.Log;

import com.cyj.libservice.WorkService;

/**
 * Created by chengyijun on 18/7/19.
 */

public class WorkServiceImpl implements WorkService {
    @Override
    public void testService() {
        Log.e("service","is work service");
    }
}
