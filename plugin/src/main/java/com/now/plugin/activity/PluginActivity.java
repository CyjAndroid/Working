package com.now.plugin.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import com.android.annotation.AutoAssign;
import com.android.annotation.Dispatcher;
import com.android.cyj.router.Router;

import java.io.Serializable;

/**
 * Created by Cyj on 18/1/30.
 */
@Dispatcher("cyj://test")
public class PluginActivity extends Activity {
    @AutoAssign
    String name;
    @AutoAssign
    int age;
    @AutoAssign
    Parcelable obj;
    @AutoAssign
    Serializable obj_test;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Router.getInstance().inject(this);
        TextView view = new TextView(this);
        view.setText("this is plugin " + name + "----" + age+"----"+obj.toString()+"--"+obj_test.toString());
        setContentView(view);
    }
}
