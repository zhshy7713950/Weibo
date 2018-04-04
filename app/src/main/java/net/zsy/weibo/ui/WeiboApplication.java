package net.zsy.weibo.ui;

import android.app.Application;
import android.content.Context;

import net.zsy.weibo.data.WeiboDataManager;
import net.zsy.weibo.data.async.AsyncDataManager;

/**
 * Created by Android on 2018/4/4.
 */

public class WeiboApplication extends Application {

    private static Context ctx;

    @Override
    public void onCreate() {
        super.onCreate();
        ctx = getApplicationContext();

        initializeInThread();
    }

    private void initializeInThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

                WeiboDataManager.getInstance().initialize(AsyncDataManager.getInstance());
            }
        }).start();
    }

    public static Context getAppContext(){
        return ctx;
    }
}
