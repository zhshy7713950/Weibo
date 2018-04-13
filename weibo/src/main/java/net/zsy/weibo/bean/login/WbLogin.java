package net.zsy.weibo.bean.login;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import net.zsy.weibo.bean.WeiboTypeBean;

public interface WbLogin {

    enum LoginStatus{
        LOGOUT,
        LOGON
    }

    Oauth2AccessToken getOauth2AccessToken();

    void setOauth2AccessToken(Oauth2AccessToken oauth2AccessToken);

    String getAccessToken();

    void changeMainView(WeiboTypeBean weiboTypeBean);

    LoginStatus getStatus();
}
