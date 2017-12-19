package com.now.working.ui.view;

import com.now.working.data.bean.News;

import java.util.List;

/**
 * Created by Cyj on 17/12/19.
 */

public interface NewsDataView {
    void notifyNewsData(List<News> list);
}
