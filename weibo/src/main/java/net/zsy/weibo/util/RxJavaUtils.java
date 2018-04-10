package net.zsy.weibo.util;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Android on 2018/4/10.
 */

public class RxJavaUtils {

    public static CompositeDisposable createDisposable(){
        return new CompositeDisposable();
    }
}
