package com.now.working.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by Cyj on 17/12/17.
 */

public class ScrollToolbar extends ViewGroup {
    public ScrollToolbar(Context context) {
        this(context, null);
    }

    public ScrollToolbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }


}
