package com.now.working.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.android.annotation.Dispatcher;

/**
 * Created by Cyj on 18/1/28.
 */
@Dispatcher("cyj://stub")


public class StubActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView view = new TextView(this);
        view.setText("this is StubActivity");
        setContentView(view);
    }
}
