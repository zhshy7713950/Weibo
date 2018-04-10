package net.zsy.weibo.data.async;

import android.content.Context;

import net.zsy.weibo.ui.WeiboApplication;
import net.zsy.weibo.util.Logger;
import net.zsy.weibo.util.WeiboUtil;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Android on 2018/4/4.
 */

public class AsyncDataManager {

    public final static int NETWORK_TIMEOUT_SECS = 10;

    private Context ctx;
    private Api api;
    private ApiHandler apiHandler;
    private OkHttpClient.Builder httpBuilder;

    private AsyncDataManager(){
        ctx = WeiboApplication.getAppContext();
    }

    private static class AsyncDataManagerHolder{
        private static AsyncDataManager INSTANCE = new AsyncDataManager();
    }

    public static AsyncDataManager getInstance(){
        return AsyncDataManagerHolder.INSTANCE;
    }

    public AsyncDataManager initialize(){
        httpBuilder = new OkHttpClient.Builder();
        httpBuilder.connectTimeout(NETWORK_TIMEOUT_SECS, TimeUnit.SECONDS)
                .readTimeout(NETWORK_TIMEOUT_SECS, TimeUnit.SECONDS)
                .writeTimeout(NETWORK_TIMEOUT_SECS, TimeUnit.SECONDS);

        if (Logger.isDebug())
            httpBuilder.addInterceptor(new LoggingInterceptor());

//        setupTrustAllCerts(httpBuilder);

        api = new Retrofit.Builder()
                .baseUrl(WeiboUtil.SINA_BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpBuilder.build())
                .build()
                .create(Api.class);

        apiHandler = new ApiHandler(api);
        return this;
    }

//    public void addQueryParameter(HashMap<String,String> parameter){
//        httpBuilder.addInterceptor(chain -> {
//            Request original = chain.request();
//            HttpUrl originalHttpUrl = original.url();
//
//            HttpUrl.Builder builder = originalHttpUrl.newBuilder();
//
//            Iterator<Map.Entry<String, String>> parameterIt = parameter.entrySet().iterator();
//            while (parameterIt.hasNext()){
//                Map.Entry<String, String> entry = parameterIt.next();
//                builder.addQueryParameter(entry.getKey(),entry.getValue());
//            }
//
//            Request.Builder requestBuilder = original.newBuilder()
//                    .url(builder.build());
//
//            Request request = requestBuilder.build();
//            return chain.proceed(request);
//        });
//    }

    public static void setupTrustAllCerts(OkHttpClient.Builder builder) {
        try {
            X509TrustManager trustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }
            };
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{trustManager}, null);
            builder.sslSocketFactory(sslContext.getSocketFactory(),
                    trustManager);
            builder.hostnameVerifier((hostname,session) -> true);
        } catch (Exception e) {

        }
    }

    public ApiHandler getApiHandler(){
        return apiHandler;
    }
}
