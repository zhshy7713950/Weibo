package net.zsy.weibo.data.async;


import android.content.Context;
import android.support.design.widget.Snackbar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.zsy.weibo.R;
import net.zsy.weibo.bean.SnackbarBean;
import net.zsy.weibo.bean.WbErrorBean;
import net.zsy.weibo.bean.WeiboSettingHelper;
import net.zsy.weibo.bean.login.WbLoginHelper;
import net.zsy.weibo.ui.WeiboApplication;
import net.zsy.weibo.ui.main.MainActivity;
import net.zsy.weibo.util.WeiboUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Created by Android on 2018/4/9.
 */

public class ApiHandler implements Api {

    private Api api;
    private Context ctx;

    public ApiHandler(Api api) {
        this.api = api;
        ctx = WeiboApplication.getAppContext();
    }

    @Override
    public Observable<String> userInfo(String uid) {
        return handle(api.userInfo(uid));
    }

    @Override
    public Observable<String> publicTimeLine(String accessToken, int page, int count) {
        return handle(api.publicTimeLine(accessToken,page,count));
    }


    private <T> Observable<T> handle(Observable<T> observable){
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(this::handleError);
    }

    private Observable handleError(Throwable throwable){
        boolean hasHandle = false;
        if (throwable instanceof HttpException){
            HttpException httpException = (HttpException) throwable;
            try {
                WbErrorBean wbErrorBean = new Gson().fromJson(httpException.response().errorBody().string(),WbErrorBean.class);
                operateError(wbErrorBean);
                hasHandle = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(throwable  instanceof WbException){
            WbException wbException = (WbException) throwable;
            operateError(wbException.getWbErrorBean());
            hasHandle = true;
        }
        if (hasHandle){
            return Observable.create(emitter -> emitter.onComplete());
        }else {
            return Observable.create(emitter -> {
                emitter.onError(throwable);
                emitter.onComplete();});
        }
    }

    private void operateError(WbErrorBean errorBean){
        switch (errorBean.getError_code()){
            case WeiboUtil.ERROR_WEIBO_TOKEN:
            case WeiboUtil.ERROR_WEIBO_EXPRIED_TOKEN:
                postReLogin();
                break;
            case WeiboUtil.ERROR_WEIBO_AUTH_RELIEVE:
            case WeiboUtil.ERROR_WEIBO_PERMISSION_DENIED:
                postTip(ctx.getString(R.string.weibo_permission_denied));
                break;
        }
    }

    private void postTip(String msg){
        EventBus.getDefault().post(SnackbarBean.create()
                .setMessage(msg)
                .setDuration(Snackbar.LENGTH_LONG));
    }

    private void postReLogin(){
        WbLoginHelper.getHelper().logOut();
        EventBus.getDefault().post(SnackbarBean.create()
                        .setMessage(ctx.getString(R.string.weibo_access_token))
                        .setDuration(Snackbar.LENGTH_INDEFINITE)
                        .setAction1(ctx.getString(R.string.weibo_confirm))
                        .setOnClickListener1(view -> {
                            WeiboApplication.finishActivity(MainActivity.class);
                            EventBus.getDefault().postSticky(WeiboSettingHelper.getHelper().createWeiboTypeBean());
                        }));
    }

}
