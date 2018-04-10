package net.zsy.weibo.data.async;

import net.zsy.weibo.bean.Movie;
import net.zsy.weibo.util.WeiboUtil;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Query;

/**
 * Created by Android on 2018/4/4.
 */

public interface Api {

    @GET(WeiboUtil.SINA_OAUTH2)
    Observable<String> authorize(@Query("client_id")String clientId, @Query("redirect_uri") String redirectUri);

}
