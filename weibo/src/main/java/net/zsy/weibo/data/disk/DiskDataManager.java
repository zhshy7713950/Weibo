package net.zsy.weibo.data.disk;

import android.content.Context;
import android.content.SharedPreferences;

import net.zsy.weibo.ui.WeiboApplication;
import net.zsy.weibo.util.WeiboUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Android on 2018/4/10.
 */

public class DiskDataManager {

    private Context ctx;
    private SharedPreferences mSharedPref;
    private SharedPreferences.Editor mEditor;

    private DiskDataManager(){
        ctx = WeiboApplication.getAppContext();
    }

    private static class DiskDataManagerHolder{
        private static DiskDataManager INSTANCE = new DiskDataManager();
    }

    public static DiskDataManager getInstance(){
        return DiskDataManagerHolder.INSTANCE;
    }

    public DiskDataManager initialize(){
        mSharedPref = ctx.getSharedPreferences(WeiboUtil.APP_CACHE_ROOT,Context.MODE_PRIVATE);
        mEditor = mSharedPref.edit();
        return this;
    }

    private Function<String,String> funGetString = tag -> mSharedPref.getString(tag,"");
    private Function<String,Boolean> funGetBoolean = tag -> mSharedPref.getBoolean(tag,false);
    private Function<String,Integer> funGetInteger = tag -> mSharedPref.getInt(tag,-1);
    private Function<String,Long> funGetLong = tag -> mSharedPref.getLong(tag,-1);

    public Observable<String> getString(String tag){return getSharedPrefData(tag,funGetString);}
    public Observable<Integer> getInt(String tag){return getSharedPrefData(tag,funGetInteger);}
    public Observable<Boolean> getBoolean(String tag){return getSharedPrefData(tag,funGetBoolean);}
    public Observable<Long> getLong(String tag){return getSharedPrefData(tag,funGetLong);}

    private BiConsumer<String,String> funSaveString = (tag,data) -> {mEditor.putString(tag,data);mEditor.commit();};
    private BiConsumer<String,Boolean> funSaveBoolean = (tag,data) -> {mEditor.putBoolean(tag,data);mEditor.commit();};
    private BiConsumer<String,Integer> funSaveInteger = (tag,data) -> {mEditor.putInt(tag,data);mEditor.commit();};
    private BiConsumer<String,Long> funSaveLong = (tag,data) -> {mEditor.putLong(tag,data);mEditor.commit();};

    public void saveString(String tag,String data){ saveSharedPrefData(tag,data,funSaveString); }
    public void saveInt(String tag,Integer data){ saveSharedPrefData(tag,data,funSaveInteger); }
    public void saveBoolean(String tag,Boolean data){ saveSharedPrefData(tag,data,funSaveBoolean);}
    public void saveLong(String tag,Long data){ saveSharedPrefData(tag,data,funSaveLong); }

    private <R> Observable<R> getSharedPrefData(String tag, Function<String,R> function){
        return Observable.just(tag)
                .map(s -> function.apply(s))
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private <T> void saveSharedPrefData(String tag,T data, BiConsumer<String,T> biConsumer){
        Observable.just(tag)
                .subscribeOn(Schedulers.single())
                .observeOn(Schedulers.single())
                .subscribe(s -> biConsumer.accept(s,data));
    }

}
