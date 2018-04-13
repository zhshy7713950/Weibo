package net.zsy.weibo.bean.login;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import net.zsy.weibo.bean.WeiboTypeBean;
import net.zsy.weibo.data.WeiboDataManager;

import java.util.HashMap;

public class WbLoginHelper implements WbLogin{

    private WbLogin curWbLogin;
    private WbLogin wbLogOn;
    private WbLogin wbLogOut;

    private WbLoginHelper() {
    }

    @Override
    public Oauth2AccessToken getOauth2AccessToken() {
        return curWbLogin == null ? null : curWbLogin.getOauth2AccessToken();
    }

    @Override
    public void setOauth2AccessToken(Oauth2AccessToken oauth2AccessToken) {
        if (curWbLogin != null) curWbLogin.setOauth2AccessToken(oauth2AccessToken);
    }

    @Override
    public String getAccessToken() {
        return curWbLogin == null ? null : curWbLogin.getAccessToken();
    }

    @Override
    public void changeMainView(WeiboTypeBean weiboTypeBean) {
        if (curWbLogin != null) curWbLogin.changeMainView(weiboTypeBean);
    }

    @Override
    public LoginStatus getStatus() {
        return curWbLogin == null ? LoginStatus.LOGOUT : curWbLogin.getStatus();
    }

    public boolean isLogin() {
        return curWbLogin == null ? false : (curWbLogin.getStatus() == WbLogin.LoginStatus.LOGON);
    }

    public void logOn(Oauth2AccessToken oauth2AccessToken){
        if (wbLogOn == null)
            wbLogOn = new WbLogon();
        initRetrofit(oauth2AccessToken);
        wbLogOn.setOauth2AccessToken(oauth2AccessToken);
        curWbLogin = wbLogOn;
    }

    private void initRetrofit(Oauth2AccessToken oauth2AccessToken){
        HashMap<String,String> map = new HashMap<>();
        map.put("access_token",oauth2AccessToken.getToken());
        WeiboDataManager.getInstance().addAsyncRequestParameter(map);
        WeiboDataManager.getInstance().initRetrofit();
    }

    public void logOut(){
        if (wbLogOut == null) wbLogOut = new WbLogOut();
        curWbLogin = wbLogOut;
    }

    public static WbLoginHelper getHelper() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final WbLoginHelper INSTANCE = new WbLoginHelper();
    }

}
