package com.now.working.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Cyj on 17/12/18.
 */

public abstract class BaseFragment extends Fragment {
    public View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        int layoutID = getContentViewLayoutID();
        view = null;
        if (layoutID != 0) {
            view = inflater.inflate(layoutID, null);
            return view;
        }
        view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        bindData();
    }

    protected abstract int getContentViewLayoutID();

    protected abstract void initView();

    protected abstract void bindData();

}
