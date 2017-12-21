package com.now.working.data;

import com.now.working.WorkApplication;
import com.now.working.data.bean.News;
import com.now.working.data.db.DaoMaster;
import com.now.working.data.db.DaoSession;
import com.now.working.data.db.NewsDao;

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
        dao.insert(news);
    }

    public void deleteNewsById(Long id) {
        News news = queryNewsById(id);
        if (news != null) {
            getNewsDao().deleteByKey(id);
        }
    }

    public News queryNewsById(Long id) {
        return getNewsDao().queryBuilder().
                where(NewsDao.Properties.Id.eq(id)).build().unique();
    }

    public void deleteAllNews() {
        getNewsDao().deleteAll();
    }

    public void queryBySq(String sq){
    }
}
