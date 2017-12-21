package com.now.working.model;

import com.now.working.data.bean.News;
import com.now.working.listener.OnNewsDataLoadListener;

import java.util.List;

/**
 * Created by Cyj on 17/12/19.
 */

public interface NewsDataModel {
    List<News> loadDBNews();

    void loadNewsData(OnNewsDataLoadListener listener);

    void saveNews(List<News> list);
}
