package net.zsy.weibo.data.async;

import net.zsy.weibo.util.WeiboUtil;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Android on 2018/4/4.
 */

public interface Api {

    @FormUrlEncoded
    @POST(WeiboUtil.SINA_OAUTH2_ACCESS_TOKEN)
    Observable<String> accessToken(@FieldMap Map<String,String> params);


}
