package com.now.working.presenter;

import android.util.Log;

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
        final List<News> data = newsDataModel.loadDBNews();
        if (data != null && data.size() > 0) {
            Log.e("cyj", "in db : " + data.size());
            view.notifyNewsData(data);
        }

        newsDataModel.loadNewsData(new OnNewsDataLoadListener() {
            @Override
            public void completed(List<News> list) {
                if (list != null && list.size() > 0) {
                    Log.e("cyj", "is new : " + list.size());

                    saveData(list);

                    if (data != null && data.size() > 0) {
                        list.addAll(data);
                    }
                    view.notifyNewsData(list);
                }
            }
        });
    }

    public void saveData(List<News> list) {
        newsDataModel.saveNews(list);
    }
}
