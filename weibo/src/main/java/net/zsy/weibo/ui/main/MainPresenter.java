package net.zsy.weibo.ui.main;

import android.text.TextUtils;

import net.zsy.weibo.bean.WeiboSettingHelper;
import net.zsy.weibo.data.WeiboDataManager;
import net.zsy.weibo.util.RxJavaUtils;
import net.zsy.weibo.util.WeiboUtil;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

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
        Observable<String> obToken = WeiboDataManager.getInstance().getSpString(WeiboSettingHelper.ZWEIBO_ACCESS_TOKEN);
        Observable<Long> obCreateAt = WeiboDataManager.getInstance().getSpLong(WeiboSettingHelper.ZWEIBO_TOKEN_CREATE_AT);
        Observable<Long> obExpriesIn = WeiboDataManager.getInstance().getSpLong(WeiboSettingHelper.ZWEIBO_TOKEN_EXPIRE_IN);

        mDisposable.add(Observable.zip(obToken,obCreateAt,obExpriesIn,(token,createAt,expriesIn)->{
            if(TextUtils.isEmpty(token)) return true;
            if(createAt > 0 && expriesIn > 0 && createAt + expriesIn - WeiboUtil.getCurrentTimeMillis() <= 180) return true;
            else return false;
        }).subscribe(needLogin -> {
            if(needLogin) view.showLoginView();
            else view.showWeiboView();
        }));
    }
}
