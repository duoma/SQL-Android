package com.greendao.hepler;

import android.content.Context;

import com.ang.AngHelper;
import com.greendao.dao.DaoMaster;
import com.greendao.dao.DaoSession;

/**
 * @author duoma
 * @date 2019/03/13
 */
public class DbManager {

    private static final String DATABASE_NAME = "greendao.db";

    private static volatile DbManager mInstance;

    private static volatile DaoSession mDaoSession;

    public static Context getContext(){
        return AngHelper.getContext();
    }

    private DbManager() {
        CustomOpenHelper mDevOpenHelper = new CustomOpenHelper(getContext(), DATABASE_NAME, null);
        DaoMaster daoMaster = new DaoMaster(mDevOpenHelper.getWritableDb());
        mDaoSession = daoMaster.newSession();
    }

    public static DbManager getInstance() {
        if (mInstance == null) {
            synchronized (DbManager.class) {
                if (mInstance == null) {
                    mInstance = new DbManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取DaoSession
     */
    public static DaoSession getDaoSession() {
        if (mDaoSession == null) {
            synchronized (DbManager.class) {
                if (mDaoSession == null) {
                    getInstance();
                }
            }
        }
        return mDaoSession;
    }
}
