package net.zsy.weibo.ui.login;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.zsy.weibo.R;
import net.zsy.weibo.ui.base.BaseFragment;
import net.zsy.weibo.ui.widget.WbWebView;
import net.zsy.weibo.util.WeiboUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Android on 2018/4/9.
 */

public class OAuth2Web extends BaseFragment {

    @BindView(R.id.wwv_login)
    WbWebView wwvLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.login_layout, container, false);
        unbinder = ButterKnife.bind(this, viewRoot);
        initView();
        return viewRoot;
    }

    private void initView() {
        String url = buildAuthorizeUrl();
        wwvLogin.loadUrl(url);
        wwvLogin.registerAction()
                .map(actionHolder -> Uri.parse(url))
                .map(uri -> uri.getQueryParameter("code"))
                .filter(s -> !TextUtils.isEmpty(s))
                .subscribe(s -> {

                });
    }

    private String buildAuthorizeUrl() {
        StringBuilder sb = new StringBuilder(WeiboUtil.SINA_BASE_URL);
        sb.append(WeiboUtil.SINA_OAUTH2)
                .append("?")
                .append("client_id=")
                .append(WeiboUtil.SINA_APP_KEY)
                .append("&")
                .append("redirect_uri=")
                .append(WeiboUtil.SINA_REDIRECT_URI);
        return sb.toString();
    }

}
