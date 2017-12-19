package com.now.working.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.now.working.R;

/**
 * Created by Cyj on 17/12/19.
 */

public class NewsItemHolder2 extends NewsItemHolder {

    public TextView newsTitle;
    public ImageView newsImage;

    public NewsItemHolder2(View itemView) {
        super(itemView);
        newsTitle = itemView.findViewById(R.id.news_title);
        newsImage = itemView.findViewById(R.id.news_image);
    }
}