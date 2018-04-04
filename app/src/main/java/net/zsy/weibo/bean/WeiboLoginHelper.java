package net.zsy.weibo.bean;

import android.content.Context;

import net.zsy.weibo.ui.WeiboApplication;

/**
 * Created by Android on 2018/4/4.
 */

public class WeiboLoginHelper {

    private Context ctx;

    private WeiboLoginHelper(){
        ctx = WeiboApplication.getAppContext();
    }

    private static class WeiboLoginHelperHolder{
        private static WeiboLoginHelper INSTANCE = new WeiboLoginHelper();
    }

    public static WeiboLoginHelper getHelper(){
        return WeiboLoginHelperHolder.INSTANCE;
    }
}
