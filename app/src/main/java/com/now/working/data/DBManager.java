package com.now.working.data;

import com.now.working.WorkApplication;
import com.now.working.data.bean.News;
import com.now.working.data.db.DaoMaster;
import com.now.working.data.db.DaoSession;
import com.now.working.data.db.NewsDao;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Cyj on 17/12/21.
 */

public class DBManager {
    public static final String DB_NAME = "news.db";

    private static DBManager mInstance = null;

    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private DaoMaster.DevOpenHelper devOpenHelper;

    private DBManager() {
        devOpenHelper = new DaoMaster.DevOpenHelper(WorkApplication.getApplication(), DB_NAME, null);
        daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoSession = daoMaster.newSession();
    }

    public static DBManager getInstence() {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager();
                }
            }
        }
        return mInstance;
    }

    private NewsDao getNewsDao() {
        return daoSession.getNewsDao();
    }

    public void insertNews(News news) {
        NewsDao dao = getNewsDao();
        dao.insertOrReplace(news);
    }

    public void insertNews(List<News> news) {
        NewsDao dao = getNewsDao();
        dao.insertOrReplaceInTx(news);
    }

    public void deleteNewsById(Long id) {
        queryNewsById(id).filter(new Func1<News, Boolean>() {
            @Override
            public Boolean call(News news) {
                return news != null;
            }
        }).subscribe(new Action1<News>() {
            @Override
            public void call(News news) {
                getNewsDao().deleteByKey(news.getId());
            }
        });
    }

    public Observable<News> queryNewsById(Long id) {
        return Observable.just(getNewsDao().queryBuilder().
                where(NewsDao.Properties.Id.eq(id)).build().unique());
    }

    public void deleteAllNews() {
        getNewsDao().deleteAll();
    }

    public List<News> queryRaw(String where, String... selectionArg) {
        return getNewsDao().queryRaw(where, selectionArg);
    }

    public List<News> queryAllNews() {
        return getNewsDao().loadAll();
    }
}
