package net.zsy.weibo.ui.base;

import android.app.Fragment;

import butterknife.Unbinder;

/**
 * Created by Android on 2018/4/9.
 */

public class BaseFragment<T extends BasePresenter> extends Fragment {

    protected T presenter;

    protected Unbinder unbinder;

    @Override
    public void onResume() {
        super.onResume();
        if(presenter != null) presenter.attachView();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(presenter != null) presenter.detachView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder != null) unbinder.unbind();
    }
}
