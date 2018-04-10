package net.zsy.weibo.ui.login;

import android.app.Activity;
import android.app.Fragment;

import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Android on 2018/4/10.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private SsoHandler mSsoHandler;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        view.setPresenter(this);
        mSsoHandler = new SsoHandler(((Fragment)view).getActivity());
    }

    @Override
    public void accessToken() {
        mSsoHandler.authorizeWeb(new WbAuthListener() {
            @Override
            public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
                Observable.just(oauth2AccessToken)
                        .doOnNext(token -> AccessTokenKeeper.writeAccessToken(((Fragment)view).getActivity(), token))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(token -> view.accessToken(oauth2AccessToken));
            }

            @Override
            public void cancel() {

            }

            @Override
            public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
                System.out.print(wbConnectErrorMessage.getErrorMessage());
            }
        });
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
    }
}
