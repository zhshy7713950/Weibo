package net.zsy.weibo.util;

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

    public static final String SINA_BASE_URL = "https://api.weibo.com/";

    public static final String SINA_OAUTH2_AUTHORIZE = "oauth2/authorize";
    public static final String SINA_OAUTH2_ACCESS_TOKEN = "oauth2/access_token";

    public static long getCurrentTimeMillis(){return System.currentTimeMillis();}

}
