package com.greendao.hepler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.greendao.dao.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * @author duoma
 * @date 2019/03/13
 */
public class CustomOpenHelper extends DaoMaster.OpenHelper {

    public CustomOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    /**
     * 数据库升级
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {

    }
}
