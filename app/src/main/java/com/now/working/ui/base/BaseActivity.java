package com.now.working.ui.base;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Cyj on 17/12/17.
 */

public abstract class BaseActivity extends LifecycleActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getContentViewLayoutID() != 0) {
            setContentView(getContentViewLayoutID());
        }
    }

    protected abstract int getContentViewLayoutID();

}
