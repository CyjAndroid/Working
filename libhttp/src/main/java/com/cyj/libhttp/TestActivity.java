package com.cyj.libhttp;

import android.app.Activity;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by chengyijun on 18/6/18.
 */
import com.android.annotation.Dispatcher;


public class TestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_main_layout);

        getContentResolver().registerContentObserver(Uri.parse("content://com.cyj.work/test"),
                false, new ContentObserver(null) {
                    @Override
                    public void onChange(boolean selfChange) {
                        super.onChange(selfChange);
                        Log.e("lib","onChange");
                    }
                });
    }
}
