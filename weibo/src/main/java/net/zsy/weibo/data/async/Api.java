package net.zsy.weibo.data.async;

import net.zsy.weibo.util.WeiboUtil;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Android on 2018/4/4.
 */

public interface Api {

    @GET(WeiboUtil.SINA_USER_SHOW)
    Observable<String> userInfo(@Query("uid")String uid);

    @GET(WeiboUtil.SINA_PUBLIC_TIMELINE)
    Observable<String> publicTimeLine(@Query("access_token")String accessToken,@Query("page")int page,@Query("count")int count);


}
