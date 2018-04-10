package net.zsy.weibo.ui.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Android on 2018/4/9.
 */

public class BaseFragment <T extends BasePresenter> extends Fragment {

    protected Unbinder unbinder;
    protected CompositeDisposable mDisposable;
    protected T presenter;

    @Override
    public void onResume() {
        super.onResume();
        if(presenter != null) presenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(presenter != null) presenter.unsubscribe();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder != null) unbinder.unbind();
        if(mDisposable != null) mDisposable.clear();
    }
}
