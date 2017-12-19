package com.now.working.model;

import com.now.working.data.bean.News;
import com.now.working.listener.OnNewsDataLoadListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cyj on 17/12/19.
 */

public class NewsDataModelImpl implements NewsDataModel {

    @Override
    public void loadNewsData(OnNewsDataLoadListener listener) {
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

        listener.completed(list);
    }
}
