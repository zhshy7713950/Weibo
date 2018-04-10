package net.zsy.weibo.bean;

import android.content.Context;

import net.zsy.weibo.ui.WeiboApplication;

/**
 * Created by Android on 2018/4/4.
 */

public class WeiboSettingHelper {

    private Context ctx;

    private WeiboSettingHelper(){
        ctx = WeiboApplication.getAppContext();
    }

    private static class WeiboSettingHelperHolder{
        private static WeiboSettingHelper INSTANCE = new WeiboSettingHelper();
    }

    public static WeiboSettingHelper getHelper(){
        return WeiboSettingHelperHolder.INSTANCE;
    }

    public static final String ZWEIBO_ACCESS_TOKEN = "zweibo_access_token";
    public static final String ZWEIBO_TOKEN_CREATE_AT = "zweibo_token_create_at";
    public static final String ZWEIBO_TOKEN_EXPIRE_IN = "zweibo_token_expire_in";
}
