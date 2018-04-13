package net.zsy.weibo.bean.login;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import net.zsy.weibo.bean.WeiboTypeBean;
import org.greenrobot.eventbus.EventBus;

public class WbLogon implements WbLogin {

    private Oauth2AccessToken oauth2AccessToken;

    @Override
    public Oauth2AccessToken getOauth2AccessToken(){
        return oauth2AccessToken;
    }

    @Override
    public void setOauth2AccessToken(Oauth2AccessToken oauth2AccessToken){
        this.oauth2AccessToken = oauth2AccessToken;
    }

    @Override
    public String getAccessToken(){
        if(oauth2AccessToken != null) return oauth2AccessToken.getToken();
        else return null;
    }

    @Override
    public void changeMainView(WeiboTypeBean weiboTypeBean) {
        EventBus.getDefault().postSticky(weiboTypeBean);
    }

    @Override
    public LoginStatus getStatus() {
        return LoginStatus.LOGON;
    }
}
