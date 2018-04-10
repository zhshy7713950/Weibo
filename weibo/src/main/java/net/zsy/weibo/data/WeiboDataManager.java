package net.zsy.weibo.data;

import android.content.Context;

import net.zsy.weibo.data.async.ApiHandler;
import net.zsy.weibo.data.async.AsyncDataManager;
import net.zsy.weibo.data.disk.DiskDataManager;
import net.zsy.weibo.ui.WeiboApplication;

import io.reactivex.Observable;

/**
 * Created by Android on 2018/4/4.
 */

public class WeiboDataManager {

    private Context ctx;
    private AsyncDataManager asyncDataManager;
    private DiskDataManager diskDataManager;

    private WeiboDataManager(){
        ctx = WeiboApplication.getAppContext();
    }

    private static class WeiboDataManagerHolder{
        private static WeiboDataManager INSTANCE = new WeiboDataManager();
    }

    public static WeiboDataManager getInstance(){
        return WeiboDataManagerHolder.INSTANCE;
    }

    public WeiboDataManager initialize(AsyncDataManager asyncDataManager, DiskDataManager diskDataManager){
        this.asyncDataManager = asyncDataManager.initialize();
        this.diskDataManager = diskDataManager.initialize();
        return this;
    }

    public ApiHandler getApiHandler(){
        return asyncDataManager.getApiHandler();
    }

    public Observable<String> getSpString(String tag){return diskDataManager.getString(tag);}
    public Observable<Boolean> getSpBoolean(String tag){return diskDataManager.getBoolean(tag);}
    public Observable<Integer> getSpInt(String tag){return diskDataManager.getInt(tag);}
    public Observable<Long> getSpLong(String tag){return diskDataManager.getLong(tag);}

    public void saveSpString(String tag,String data){diskDataManager.saveString(tag,data);}
    public void saveSpBoolean(String tag,Boolean data){diskDataManager.saveBoolean(tag,data);}
    public void saveSpInt(String tag,Integer data){diskDataManager.saveInt(tag,data);}
    public void saveSpLong(String tag,Long data){diskDataManager.saveLong(tag,data);}

}
