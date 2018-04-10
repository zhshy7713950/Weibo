package net.zsy.weibo.data.async;

import net.zsy.weibo.bean.Movie;

import io.reactivex.Observable;
import retrofit2.http.Query;

/**
 * Created by Android on 2018/4/9.
 */

public class ApiHandler implements Api {

    private Api api;

    public ApiHandler(Api api) {
        this.api = api;
    }

    @Override
    public Observable<String> authorize(@Query("client_id") String clientId, @Query("redirect_uri") String redirectUri) {
        return api.authorize(clientId,redirectUri);
    }

}
