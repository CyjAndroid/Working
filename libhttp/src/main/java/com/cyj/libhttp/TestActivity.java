package com.cyj.libhttp;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by chengyijun on 18/6/18.
 */
import com.android.annotation.Dispatcher;

@Dispatcher("easyrouter://test")
public class TestActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_main_layout);
    }
}
