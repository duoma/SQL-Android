package com.duoma.sql;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.ang.database.KDB;
import com.ang.utils.ThreadPoolUtils;
import com.greendao.dao.DemoGreen;
import com.greendao.dao.DemoGreenDao;
import com.greendao.hepler.DbManager;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.kdb.DemoKDB;
import com.liteorm.DemoLiteOrm;
import com.litepal.DemoLitePal;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.objectbox.DemoObjectBox;
import com.objectbox.DemoObjectBox_;
import com.ormlite.DatabaseHelper;
import com.ormlite.DemoOrmLite;

import org.litepal.LitePal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

public class MainActivity extends Activity implements View.OnClickListener {

    private int num;
    private EditText ed_num;
    private CheckBox checkbox;

    private TextView tv_kdb;
    private TextView tv_objectbox;
    private TextView tv_greendao;
    private TextView tv_liteorm;
    private TextView tv_ormlite;
    private TextView tv_litepal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_kdb = findViewById(R.id.tv_kdb);
        tv_objectbox = findViewById(R.id.tv_objectbox);
        tv_greendao = findViewById(R.id.tv_greendao);
        tv_liteorm = findViewById(R.id.tv_liteorm);
        tv_ormlite = findViewById(R.id.tv_ormlite);
        tv_litepal = findViewById(R.id.tv_litepal);

        ed_num = findViewById(R.id.ed_num);
        findViewById(R.id.btn_start).setOnClickListener(this);

        checkbox = findViewById(R.id.checkbox);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                String text = ed_num.getText().toString().trim();

                if (!TextUtils.isEmpty(text)) {
                    num = Integer.valueOf(text);

                    tv_kdb.setText("KDB");
                    tv_objectbox.setText("ObjectBox-2.3.4");
                    tv_greendao.setText("GreenDao-3.2.2");
                    tv_liteorm.setText("LiteOrm-1.9.1");
                    tv_ormlite.setText("OrmLite-5.1");
                    tv_litepal.setText("LitePal-3.0.0");

                    kdb();
                    objectBox();
                    greenDao();
                    liteOrm();
                    ormLite();
                    litePal();
                }
                break;
        }
    }

    private void litePal() {
        ThreadPoolUtils.addThread(new Runnable() {

            @Override
            public void run() {
                long timeStart;
                long timeEnd;

                List<DemoLitePal> demoList = new ArrayList<>();
                for (int i = 0; i < num; i++) {
                    DemoLitePal demo = new DemoLitePal();
                    demo.setCounter(i);
                    demo.setUserId("222424");
                    demoList.add(demo);
                }

                timeStart = System.currentTimeMillis();
                LitePal.saveAll(demoList);
                timeEnd = System.currentTimeMillis();
                final long finalTime = timeEnd - timeStart;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_litepal.append('\n' + "增加数据库" + "-时间：" + finalTime);
                    }
                });

                timeStart = System.currentTimeMillis();
                final List<DemoLitePal> list = LitePal.where("userId = ?", "222424").find(DemoLitePal.class);
                timeEnd = System.currentTimeMillis();
                final long finalTime1 = timeEnd - timeStart;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_litepal.append('\n' + "查询数据库=" + list.size() + " - 时间：" + finalTime1);
                    }
                });

                if (checkbox.isChecked()) {
                    timeStart = System.currentTimeMillis();
                    LitePal.deleteAll(DemoLitePal.class, "userId = ?", "222424");
                    timeEnd = System.currentTimeMillis();
                    final long finalTime2 = timeEnd - timeStart;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_litepal.append('\n' + "删除数据库" + " - 时间：" + finalTime2);
                        }
                    });
                }
            }
        });
    }

    private void liteOrm() {
        ThreadPoolUtils.addThread(new Runnable() {

            @Override
            public void run() {
                long timeStart;
                long timeEnd;

                List<DemoLiteOrm> demoList = new ArrayList<>();
                for (int i = 0; i < num; i++) {
                    DemoLiteOrm demo = new DemoLiteOrm();
                    demo.setCounter(i);
                    demo.setUserId("222424");
                    demoList.add(demo);
                }

                timeStart = System.currentTimeMillis();
                CustomApp.liteOrm.save(demoList);
                timeEnd = System.currentTimeMillis();
                final long finalTime = timeEnd - timeStart;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_liteorm.append('\n' + "增加数据库" + "-时间：" + finalTime);
                    }
                });

                timeStart = System.currentTimeMillis();
                final List<DemoLiteOrm> list = CustomApp.liteOrm.query(new QueryBuilder<>(DemoLiteOrm.class)
                        .where("userId" + " LIKE ?", new String[]{"222424"}));
                timeEnd = System.currentTimeMillis();
                final long finalTime1 = timeEnd - timeStart;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_liteorm.append('\n' + "查询数据库=" + list.size() + " - 时间：" + finalTime1);
                    }
                });

                if (checkbox.isChecked()) {
                    timeStart = System.currentTimeMillis();
                    CustomApp.liteOrm.delete(list);
                    timeEnd = System.currentTimeMillis();
                    final long finalTime2 = timeEnd - timeStart;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_liteorm.append('\n' + "删除数据库" + " - 时间：" + finalTime2);
                        }
                    });
                }
            }
        });
    }

    private void ormLite() {
        ThreadPoolUtils.addThread(new Runnable() {

            @Override
            public void run() {
                long timeStart;
                long timeEnd;

                DatabaseHelper helper = DatabaseHelper.getHelper(CustomApp.getContext());

                List<DemoOrmLite> demoList = new ArrayList<>();
                for (int i = 0; i < num; i++) {
                    DemoOrmLite demo = new DemoOrmLite();
                    demo.setCounter(i);
                    demo.setUserId("222424");
                    demoList.add(demo);
                }

                timeStart = System.currentTimeMillis();
                try {
                    helper.getDemoDao().create(demoList);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                timeEnd = System.currentTimeMillis();
                final long finalTime = timeEnd - timeStart;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_ormlite.append('\n' + "增加数据库" + "-时间：" + finalTime);
                    }
                });

                timeStart = System.currentTimeMillis();
                List<DemoOrmLite> list = new ArrayList<>();
                try {
                    list = helper.getDemoDao().queryBuilder().where().eq("userId", "222424").query();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                timeEnd = System.currentTimeMillis();
                final long finalTime1 = timeEnd - timeStart;
                final List<DemoOrmLite> finalList = list;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_ormlite.append('\n' + "查询数据库=" + finalList.size() + " - 时间：" + finalTime1);
                    }
                });

                if (checkbox.isChecked()) {
                    timeStart = System.currentTimeMillis();
                    try {
                        DeleteBuilder<DemoOrmLite, Integer> deleteBuilder = helper.getDemoDao().deleteBuilder();
                        deleteBuilder.where().eq("userId", "222424");
                        deleteBuilder.delete();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    timeEnd = System.currentTimeMillis();
                    final long finalTime2 = timeEnd - timeStart;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_ormlite.append('\n' + "删除数据库" + " - 时间：" + finalTime2);
                        }
                    });
                }
            }
        });
    }

    private void greenDao() {
        ThreadPoolUtils.addThread(new Runnable() {

            @Override
            public void run() {
                long timeStart;
                long timeEnd;

                DemoGreenDao demoGreenDao = DbManager.getDaoSession().getDemoGreenDao();

                List<DemoGreen> demoList = new ArrayList<>();
                for (int i = 0; i < num; i++) {
                    DemoGreen demo = new DemoGreen();
                    demo.setCounter(i);
                    demo.setUserId("222424");
                    demoList.add(demo);
                }

                timeStart = System.currentTimeMillis();
                demoGreenDao.insertInTx(demoList);
                timeEnd = System.currentTimeMillis();
                final long finalTime = timeEnd - timeStart;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_greendao.append('\n' + "增加数据库" + "-时间：" + finalTime);
                    }
                });

                timeStart = System.currentTimeMillis();
                final List<DemoGreen> demoGreens = demoGreenDao.queryBuilder()
                        .where(DemoGreenDao.Properties.UserId.eq("222424")).list();
                timeEnd = System.currentTimeMillis();
                final long finalTime1 = timeEnd - timeStart;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_greendao.append('\n' + "查询数据库=" + demoGreens.size() + " - 时间：" + finalTime1);
                    }
                });

                if (checkbox.isChecked()) {
                    timeStart = System.currentTimeMillis();
                    demoGreenDao.deleteInTx(demoGreens);
                    timeEnd = System.currentTimeMillis();
                    final long finalTime2 = timeEnd - timeStart;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_greendao.append('\n' + "删除数据库" + " - 时间：" + finalTime2);
                        }
                    });
                }
            }
        });
    }

    private void objectBox() {
        ThreadPoolUtils.addThread(new Runnable() {

            @Override
            public void run() {
                long timeStart;
                long timeEnd;

                Box<DemoObjectBox> boxBox = CustomApp.boxStore.boxFor(DemoObjectBox.class);

                List<DemoObjectBox> demoList = new ArrayList<>();
                for (int i = 0; i < num; i++) {
                    DemoObjectBox demo = new DemoObjectBox();
                    demo.setCounter(i);
                    demo.setUserId("222424");
                    demoList.add(demo);
                }

                timeStart = System.currentTimeMillis();
                boxBox.put(demoList);
                timeEnd = System.currentTimeMillis();
                final long finalTime = timeEnd - timeStart;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_objectbox.append('\n' + "增加数据库" + "-时间：" + finalTime);
                    }
                });

                timeStart = System.currentTimeMillis();
                final List<DemoObjectBox> list = boxBox.query().equal(DemoObjectBox_.userId, "222424").build().find();
                timeEnd = System.currentTimeMillis();
                final long finalTime1 = timeEnd - timeStart;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_objectbox.append('\n' + "查询数据库=" + list.size() + " - 时间：" + finalTime1);
                    }
                });

                if (checkbox.isChecked()) {
                    timeStart = System.currentTimeMillis();
                    boxBox.remove(list);
                    timeEnd = System.currentTimeMillis();
                    final long finalTime2 = timeEnd - timeStart;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_objectbox.append('\n' + "删除数据库" + " - 时间：" + finalTime2);
                        }
                    });
                }
            }
        });
    }

    private void kdb() {
        ThreadPoolUtils.addThread(new Runnable() {

            @Override
            public void run() {
                long timeStart;
                long timeEnd;

                List<DemoKDB> demoList = new ArrayList<>();
                for (int i = 0; i < num; i++) {
                    DemoKDB demo = new DemoKDB();
                    demo.setCounter(i);
                    demo.setUserId("222424");
                    demoList.add(demo);
                }

                timeStart = System.currentTimeMillis();
                KDB.getInstance().saveInTx(demoList);
                timeEnd = System.currentTimeMillis();
                final long finalTime = timeEnd - timeStart;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_kdb.append('\n' + "增加数据库" + "-时间：" + finalTime);
                    }
                });

                timeStart = System.currentTimeMillis();
                final List<DemoKDB> demoKDBS = KDB.getInstance().findAllByWhere(DemoKDB.class,
                        "userId=" + "\"" + "222424" + "\"");
                timeEnd = System.currentTimeMillis();
                final long finalTime1 = timeEnd - timeStart;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_kdb.append('\n' + "查询数据库=" + demoKDBS.size() + " - 时间：" + finalTime1);
                    }
                });

                if (checkbox.isChecked()) {
                    timeStart = System.currentTimeMillis();
                    KDB.getInstance().deleteByWhere(DemoKDB.class,
                            "userId=" + "\"" + "222424" + "\"");
                    timeEnd = System.currentTimeMillis();
                    final long finalTime2 = timeEnd - timeStart;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_kdb.append('\n' + "删除数据库" + " - 时间：" + finalTime2);
                        }
                    });
                }
            }
        });
    }
}
