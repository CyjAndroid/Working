package com.now.working.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.now.working.R;
import com.now.working.ui.base.BaseFragment;

/**
 * Created by Cyj on 17/12/17.
 */

public class TestFragment2 extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_2,container,false);
    }
}
