package com.duoma.sql;

import android.app.Application;
import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.objectbox.MyObjectBox;

import org.litepal.LitePal;

import io.objectbox.BoxStore;

/**
 * @author duoma
 * @date 2019/02/22
 */
public class CustomApp extends Application {

    private static CustomApp mInstance;

    public static Context getContext() {
        return mInstance.getApplicationContext();
    }

    public static LiteOrm liteOrm;
    public static BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        // liteOrm
        if (liteOrm == null) {
            liteOrm = LiteOrm.newSingleInstance(this, "liteorm.db");
        }
        liteOrm.setDebugged(true); // open the log

        LitePal.initialize(this);

        boxStore = MyObjectBox.builder().androidContext(CustomApp.this).build();
    }
}
