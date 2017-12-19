package com.now.working.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.now.working.R;

/**
 * Created by Cyj on 17/12/19.
 */

public class NewsItemHolder1 extends NewsItemHolder {

    public TextView newsContent;

    public NewsItemHolder1(View itemView) {
        super(itemView);
        newsContent = (TextView) itemView.findViewById(R.id.news_content);
    }
}