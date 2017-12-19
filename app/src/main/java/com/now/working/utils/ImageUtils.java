package com.now.working.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.now.working.WorkApplication;

/**
 * Created by Cyj on 17/12/19.
 */

public class ImageUtils {
    private static Context mContext = WorkApplication.getApplication();

    public static void loadImage(ImageView view, String url) {
        Glide.with(mContext).load(url).into(view);
    }
}
