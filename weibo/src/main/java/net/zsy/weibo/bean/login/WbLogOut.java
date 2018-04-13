package net.zsy.weibo.bean.login;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import net.zsy.weibo.bean.WeiboTypeBean;

import org.greenrobot.eventbus.EventBus;

public class WbLogOut implements WbLogin {
    @Override
    public Oauth2AccessToken getOauth2AccessToken() { return null; }

    @Override
    public void setOauth2AccessToken(Oauth2AccessToken oauth2AccessToken) {}

    @Override
    public String getAccessToken() { return null; }

    @Override
    public void changeMainView(WeiboTypeBean weiboTypeBean) {
        EventBus.getDefault().postSticky(weiboTypeBean);
    }

    @Override
    public LoginStatus getStatus() {
        return LoginStatus.LOGOUT;
    }
}
