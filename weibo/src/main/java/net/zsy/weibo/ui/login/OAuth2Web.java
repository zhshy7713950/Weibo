package net.zsy.weibo.ui.login;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import net.zsy.weibo.R;
import net.zsy.weibo.ui.base.BaseFragment;
import net.zsy.weibo.util.RxJavaUtils;
import net.zsy.weibo.util.WeiboUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

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
//        String url = buildAuthorizeUrl();
//        wwvLogin.loadUrl(url);
//        mDisposable.add(wwvLogin.registerAction()
//                .map(actionHolder -> Uri.parse(actionHolder.getUrl()))
//                .map(uri -> uri.getQueryParameter("code") == null ? "" : uri.getQueryParameter("code"))
//                .filter(s -> !TextUtils.isEmpty(s))
//                .subscribe(s -> presenter.accessToken(s)));
    }

    private String buildAuthorizeUrl() {
        StringBuilder sb = new StringBuilder(WeiboUtil.SINA_BASE_URL);
        sb.append(WeiboUtil.SINA_OAUTH2_AUTHORIZE)
                .append("?")
                .append("client_id=")
                .append(WeiboUtil.SINA_APP_KEY)
                .append("&")
                .append("redirect_uri=")
                .append(WeiboUtil.SINA_REDIRECT_URI);
        return sb.toString();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        System.out.print("setLoadingIndicator" + active);
    }

    @Override
    public void accessToken(Oauth2AccessToken token) {
        /**
         * 显示当前 Token 信息。
         *
         * @param hasExisted 配置文件中是否已存在 token 信息并且合法
         */
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(
                new Date(token.getExpiresTime()));
        String format = getString(R.string.weibosdk_demo_token_to_string_format_1);
        Toast.makeText(getActivity(), String.format(format, token.getToken(), date), Toast.LENGTH_LONG).show();
    }


    @OnClick(R.id.access_token)
    public void onViewClicked() {
        presenter.accessToken();
    }
}
