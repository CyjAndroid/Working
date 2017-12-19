package com.now.working.presenter;

import com.now.working.data.bean.News;
import com.now.working.listener.OnNewsDataLoadListener;
import com.now.working.model.NewsDataModelImpl;
import com.now.working.ui.view.NewsDataView;

import java.util.List;

/**
 * Created by Cyj on 17/12/19.
 */

public class NewsPresenter {
    private NewsDataModelImpl newsDataModel;
    private NewsDataView view;

    public NewsPresenter(NewsDataView dataView) {
        this.view = dataView;
        newsDataModel = new NewsDataModelImpl();
    }

    public void loadData() {
        newsDataModel.loadNewsData(new OnNewsDataLoadListener() {
            @Override
            public void completed(List<News> list) {
                view.notifyNewsData(list);
            }
        });
    }
}
