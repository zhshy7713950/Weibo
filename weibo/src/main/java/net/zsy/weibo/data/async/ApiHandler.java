package net.zsy.weibo.data.async;


import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.FieldMap;

/**
 * Created by Android on 2018/4/9.
 */

public class ApiHandler implements Api {

    private Api api;

    public ApiHandler(Api api) {
        this.api = api;
    }


    private <T> Observable<T> handle(Observable<T> observable){
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> accessToken(@FieldMap Map<String, String> params) {
        return handle(api.accessToken(params));
    }
}
