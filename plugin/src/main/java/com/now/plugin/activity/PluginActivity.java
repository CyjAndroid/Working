package com.now.plugin.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.annotation.Dispatcher;
import com.now.plugin.R;

/**
 * Created by Cyj on 18/1/30.
 */
@Dispatcher("cyj://test")
public class PluginActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView view = new TextView(this);
        view.setText("this is plugin");
        setContentView(view);
    }
}
