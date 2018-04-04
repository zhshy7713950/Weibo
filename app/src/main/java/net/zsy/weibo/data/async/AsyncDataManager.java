package net.zsy.weibo.data.async;

import android.content.Context;

import net.zsy.weibo.ui.WeiboApplication;

/**
 * Created by Android on 2018/4/4.
 */

public class AsyncDataManager {

    private Context ctx;

    private AsyncDataManager(){
        ctx = WeiboApplication.getAppContext();
    }

    private static class AsyncDataManagerHolder{
        private static AsyncDataManager INSTANCE = new AsyncDataManager();
    }

    public static AsyncDataManager getInstance(){
        return AsyncDataManagerHolder.INSTANCE;
    }

    public AsyncDataManager initialize(){
        return this;
    }
}
