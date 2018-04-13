package net.zsy.weibo.ui.main;

import android.content.Context;

import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import net.zsy.weibo.bean.WeiboSettingHelper;
import net.zsy.weibo.bean.login.WbLoginHelper;
import net.zsy.weibo.data.WeiboDataManager;
import net.zsy.weibo.util.Logger;
import net.zsy.weibo.util.RxJavaUtils;
import net.zsy.weibo.util.WeiboUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Android on 2018/4/10.
 */

public class MainPresenter implements MainContract.Presenter {

    private CompositeDisposable mDisposable;
    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        view.setPresenter(this);
        mDisposable = RxJavaUtils.createDisposable();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mDisposable.clear();
    }

    @Override
    public void initialDisplay() {
        mDisposable.add(Observable.create((ObservableEmitter<Oauth2AccessToken> emitter)
                -> emitter.onNext(AccessTokenKeeper.readAccessToken((Context) view)))
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(accessToken ->{
                   if(accessToken.isSessionValid() && accessToken.getExpiresTime() > WeiboUtil.getCurrentTimeMillis()){
                       WbLoginHelper.getHelper().logOn(accessToken);
                   }
                   view.showWeiboView(WeiboSettingHelper.getHelper().createWeiboTypeBean());
                }));

    }

    @Override
    public void getUserInfo() {
        mDisposable.add(WeiboDataManager.getInstance().getApiHandler()
                .userInfo(WbLoginHelper.getHelper().getOauth2AccessToken().getUid())
                .subscribe(s -> view.getUserInfo(s),
                        throwable -> {
                            String string = throwable.getMessage();
                            Logger.d(string);
                        })
        );
    }
}
