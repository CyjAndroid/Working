package com.now.working.model;

import com.now.working.listener.OnNewsDataLoadListener;

/**
 * Created by Cyj on 17/12/19.
 */

public interface NewsDataModel {
    void loadNewsData(OnNewsDataLoadListener listener);
}
