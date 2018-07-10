package com.now.working.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.annotation.Dispatcher;
import com.now.working.R;
import com.now.working.WorkApplication;
import com.now.working.data.bean.News;
import com.now.working.presenter.NewsPresenter;
import com.now.working.ui.adapter.RecyclerViewAdapter;
import com.now.working.ui.base.BaseFragment;
import com.now.working.ui.view.NewsDataView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cyj on 17/12/17.
 */
@Dispatcher("cyj://fragment")
public class NewsFragment extends BaseFragment implements NewsDataView {
    private static final int RAW_COUNT = 6;

    private RecyclerView mRecyclerView;
    private List<News> data = new ArrayList<>();
    private RecyclerViewAdapter adapter = null;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {
        mRecyclerView = view.findViewById(R.id.news_recycler_view);
    }

    @Override
    protected void bindData() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(WorkApplication.getApplication(), RAW_COUNT));

        NewsPresenter newsPresenter = new NewsPresenter(this);
        newsPresenter.loadData();

    }

    @Override
    public void notifyNewsData(List<News> list) {
        data = list;
        if (adapter == null) {
            adapter = new RecyclerViewAdapter(data);
            mRecyclerView.setAdapter(adapter);
        }

        adapter.notifyDataSetChanged();
    }
}
