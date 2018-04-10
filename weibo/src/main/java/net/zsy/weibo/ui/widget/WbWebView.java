package net.zsy.weibo.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * Created by Android on 2018/4/9.
 */

public class WbWebView extends WebView {

    FlowableProcessor<Integer> progressBus;
    FlowableProcessor<ActionHolder> actionBus;

    public WbWebView(Context context) {
        super(context);
        customWebView();
    }

    public WbWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        customWebView();
    }

    public WbWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        customWebView();
    }

    private void customWebView(){
        progressBus = PublishProcessor.create();
        actionBus = PublishProcessor.create();
        //设置支持js
        getSettings().setJavaScriptEnabled(true);
        getSettings().setBuiltInZoomControls(true);
        getSettings().setDisplayZoomControls(false);
        getSettings().setUseWideViewPort(true);
        getSettings().setLoadWithOverviewMode(true);
        setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        getSettings().setSavePassword(false);
        getSettings().setGeolocationEnabled(true);
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                actionBus.onNext(new ActionHolder(view,url));
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view,final int newProgress) {
                progressBus.onNext(newProgress);
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    public Flowable<Integer> registerProgress(){
        return progressBus;
    }

    public Flowable<ActionHolder> registerAction(){
        return actionBus;
    }

    public static class ActionHolder{
        WebView webView;
        String url;

        public ActionHolder(WebView webView, String url) {
            this.webView = webView;
            this.url = url;
        }

        public WebView getWebView() {
            return webView;
        }

        public void setWebView(WebView webView) {
            this.webView = webView;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

}
