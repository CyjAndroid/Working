package com.now.working.listener;

import com.now.working.data.bean.News;

import java.util.List;

/**
 * Created by Cyj on 17/12/19.
 */

public interface OnNewsDataLoadListener {
    void completed(List<News> list);
}
