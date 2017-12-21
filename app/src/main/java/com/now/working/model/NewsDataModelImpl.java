package com.now.working.model;

import com.now.working.data.DBManager;
import com.now.working.data.bean.News;
import com.now.working.listener.OnNewsDataLoadListener;
import com.now.working.thread.SafeExecutor;
import com.now.working.thread.SafeThreadFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cyj on 17/12/19.
 */

public class NewsDataModelImpl implements NewsDataModel {
    private static final String SAVE_NEWS = "save_news";
    private static final String LOAD_NEWS = "load_news";
    private SafeExecutor saveDataExecutor;
    private SafeExecutor loadDataExecutor;

    public NewsDataModelImpl() {
        initThread();
    }

    private void initThread() {
        saveDataExecutor = new SafeExecutor(SAVE_NEWS);
        loadDataExecutor = new SafeExecutor(LOAD_NEWS);
    }

    @Override
    public List<News> loadDBNews() {
        return DBManager.getInstence().queryAllNews();
    }

    @Override
    public void loadNewsData(final OnNewsDataLoadListener listener) {
        loadDataExecutor.asyncExecute(new Runnable() {
            @Override
            public void run() {
                listener.completed(load());
            }
        });
    }


    private List<News> load() {
        List<News> list = new ArrayList<>();
        News news1 = new News();
        news1.setContent("This is one news");
        News news2 = new News();
        news2.setContent("This is two news");
        news2.setImgUrl("http://www.jcodecraeer.com/uploads/20150327/1427445293137409.jpg");
        News news3 = new News();
        news3.setContent("This is three news");
        news3.setVideoUrl("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4");
        list.add(news1);
        list.add(news2);
        list.add(news3);
        list.add(news1);
        list.add(news2);
        list.add(news3);
        list.add(news1);
        list.add(news2);
        list.add(news3);

        return list;
    }

    @Override
    public void saveNews(final List<News> list) {
        saveDataExecutor.asyncExecute(new Runnable() {
            @Override
            public void run() {
                DBManager.getInstence().insertNews(list);
            }
        });
    }
}
