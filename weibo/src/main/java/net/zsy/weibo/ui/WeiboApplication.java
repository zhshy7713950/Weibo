package net.zsy.weibo.ui;

import android.app.Application;
import android.content.Context;

import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;

import net.zsy.weibo.data.WeiboDataManager;
import net.zsy.weibo.data.async.AsyncDataManager;
import net.zsy.weibo.data.disk.DiskDataManager;
import net.zsy.weibo.util.WeiboUtil;

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
        new Thread(()->{
            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

            WbSdk.install(ctx,new AuthInfo(this, WeiboUtil.SINA_APP_KEY, WeiboUtil.SINA_REDIRECT_URI, WeiboUtil.SINA_SCOPE));

            WeiboDataManager.getInstance().initialize(AsyncDataManager.getInstance(), DiskDataManager.getInstance());
        }).start();
    }

    public static Context getAppContext(){
        return ctx;
    }
}
