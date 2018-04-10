package net.zsy.weibo.ui.login;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import net.zsy.weibo.ui.base.BasePresenter;
import net.zsy.weibo.ui.base.BaseView;

/**
 * Created by Android on 2018/4/10.
 */

public interface LoginContract {

    interface Presenter extends BasePresenter{

        void accessToken();

    }

    interface View extends BaseView<Presenter>{

        void accessToken(Oauth2AccessToken token);

    }

}
