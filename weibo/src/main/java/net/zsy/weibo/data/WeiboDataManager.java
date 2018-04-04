package net.zsy.weibo.data;

import android.content.Context;

import net.zsy.weibo.data.async.AsyncDataManager;
import net.zsy.weibo.ui.WeiboApplication;

/**
 * Created by Android on 2018/4/4.
 */

public class WeiboDataManager {

    private Context ctx;
    private AsyncDataManager asyncDataManager;

    private WeiboDataManager(){
        ctx = WeiboApplication.getAppContext();
    }

    private static class WeiboDataManagerHolder{
        private static WeiboDataManager INSTANCE = new WeiboDataManager();
    }

    public static WeiboDataManager getInstance(){
        return WeiboDataManagerHolder.INSTANCE;
    }

    public WeiboDataManager initialize(AsyncDataManager asyncDataManager){
        this.asyncDataManager = asyncDataManager.initialize();
        return this;
    }
}
