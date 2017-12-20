package com.now.working.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import com.now.working.R;

/**
 * Created by Cyj on 17/12/19.
 */

public class NewsItemHolder3 extends NewsItemHolder {

    public TextureView newsVideo;

    public NewsItemHolder3(View itemView) {
        super(itemView);
        newsVideo = itemView.findViewById(R.id.news_video);
    }
}