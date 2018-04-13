package net.zsy.weibo.ui.login;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import net.zsy.weibo.R;
import net.zsy.weibo.bean.SnackbarBean;
import net.zsy.weibo.bean.WeiboSettingHelper;
import net.zsy.weibo.bean.login.WbLoginHelper;
import net.zsy.weibo.ui.base.BaseFragment;
import net.zsy.weibo.util.RxJavaUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Android on 2018/4/9.
 */

public class OAuth2Web extends BaseFragment<LoginContract.Presenter> implements LoginContract.View {


    @BindView(R.id.access_token)
    Button accessToken;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.login_layout, container, false);
        unbinder = ButterKnife.bind(this, viewRoot);
        mDisposable = RxJavaUtils.createDisposable();
        new LoginPresenter(this);
        initView();
        return viewRoot;
    }

    private void initView() {
    }


    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
    }

    @Override
    public void accessToken(Oauth2AccessToken token) {
        WbLoginHelper.getHelper().logOn(token);
        WbLoginHelper.getHelper().changeMainView(WeiboSettingHelper.getHelper().createWeiboTypeBean());
    }


    @SuppressWarnings("unused")
    @OnClick(R.id.access_token)
    public void onViewClicked() {
        presenter.accessToken();
    }
}
