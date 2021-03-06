package com.now.working.data.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.now.working.data.bean.News;

import com.now.working.data.db.NewsDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig newsDaoConfig;

    private final NewsDao newsDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        newsDaoConfig = daoConfigMap.get(NewsDao.class).clone();
        newsDaoConfig.initIdentityScope(type);

        newsDao = new NewsDao(newsDaoConfig, this);

        registerDao(News.class, newsDao);
    }
    
    public void clear() {
        newsDaoConfig.clearIdentityScope();
    }

    public NewsDao getNewsDao() {
        return newsDao;
    }

}
