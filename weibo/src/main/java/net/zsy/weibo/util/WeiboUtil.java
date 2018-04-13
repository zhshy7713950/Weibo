package net.zsy.weibo.util;

import android.content.Context;

import net.zsy.weibo.R;
import net.zsy.weibo.bean.WbErrorBean;
import net.zsy.weibo.ui.WeiboApplication;

/**
 * Created by Android on 2018/4/9.
 */

public class WeiboUtil {

    public static final boolean DEBUG = true;

    public final static String APP_CACHE_ROOT = "ZWeibo";

    public static final String SINA_APP_KEY = "1086685261";

    public static final String SINA_APP_SECRET = "595ca2de570e7b08ae5f30ad0e778f5a";
    public static final String SINA_REDIRECT_URI = "https://api.weibo.com/oauth2/default.html";
    public static final String SINA_SCOPE =
            "email,direct_messages_read,direct_messages_write,"
            + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
            + "follow_app_official_microblog," + "invitation_write";

    public static final String SINA_BASE_URL = "https://api.weibo.com/2/";

    public static final String SINA_PUBLIC_TIMELINE = "public_timeline.json";
    public static final String SINA_USER_SHOW = "users/show.json";

    public static long getCurrentTimeMillis(){return System.currentTimeMillis();}


    /***error***/
    public static final int ERROR_WEIBO_TOKEN = 10006;
    public static final int ERROR_WEIBO_EXPRIED_TOKEN = 21327;
    public static final int ERROR_WEIBO_AUTH_RELIEVE = 21319;
    public static final int ERROR_WEIBO_PERMISSION_DENIED = 21321;

    public static WbErrorBean createError(int code){
        WbErrorBean wbErrorBean = new WbErrorBean();
        wbErrorBean.setError_code(code);
        switch (code){
            case ERROR_WEIBO_TOKEN:
            case ERROR_WEIBO_EXPRIED_TOKEN:
                wbErrorBean.setError(WeiboApplication.getAppContext().getString(R.string.weibo_access_token));
                break;
            case ERROR_WEIBO_AUTH_RELIEVE:
            case ERROR_WEIBO_PERMISSION_DENIED:
                wbErrorBean.setError(WeiboApplication.getAppContext().getString(R.string.weibo_permission_denied));
                break;
        }
        return wbErrorBean;
    }
}
