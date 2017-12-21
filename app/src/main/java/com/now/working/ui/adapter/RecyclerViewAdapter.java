package com.now.working.ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.now.working.R;
import com.now.working.WorkApplication;
import com.now.working.data.bean.News;
import com.now.working.listener.NewsTextureListener;
import com.now.working.ui.holder.NewsItemHolder;
import com.now.working.ui.holder.NewsItemHolder1;
import com.now.working.ui.holder.NewsItemHolder2;
import com.now.working.ui.holder.NewsItemHolder3;
import com.now.working.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cyj on 17/12/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<NewsItemHolder> {
    private static final String TAG = RecyclerViewAdapter.class.getSimpleName();

    private static final int TYPE_ONLY_NEWS = 0;
    private static final int TYPE_NEWS_AND_IMAGE = 1;
    private static final int TYPE_ONLY_VIDEO = 2;

    private List<News> list = new ArrayList<>();
    private Context mContext = WorkApplication.getApplication();
    private LayoutInflater mInflater;

    public RecyclerViewAdapter(List<News> newses) {
        this.list = newses;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public NewsItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsItemHolder holder;
        if (TYPE_ONLY_NEWS == viewType) {
            holder = new NewsItemHolder1(mInflater.inflate(R.layout.news_item1, parent, false));
        } else if (TYPE_NEWS_AND_IMAGE == viewType) {
            holder = new NewsItemHolder2(mInflater.inflate(R.layout.news_item2, parent, false));
        } else {
            holder = new NewsItemHolder3(mInflater.inflate(R.layout.news_item3, parent, false));
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(NewsItemHolder holder, int position) {
        int viewType = getItemViewType(position);
        News news = list.get(position);
        if (TYPE_ONLY_NEWS == viewType) {
            NewsItemHolder1 holder1 = (NewsItemHolder1) holder;
            holder1.newsContent.setText(news.getContent());
        } else if (TYPE_NEWS_AND_IMAGE == viewType) {
            NewsItemHolder2 holder2 = (NewsItemHolder2) holder;
            holder2.newsTitle.setText(news.getContent());
            ImageUtils.loadImage(holder2.newsImage, news.getImgUrl());
        } else {
            NewsItemHolder3 holder3 = (NewsItemHolder3) holder;
            holder3.newsVideo.setUp(news.getVideoUrl(),null);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position % 3 == 2) {
                        return 6;
                    } else {
                        return 3;
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position % 3;
    }

}


