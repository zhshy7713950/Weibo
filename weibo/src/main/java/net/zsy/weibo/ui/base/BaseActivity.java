package net.zsy.weibo.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import io.reactivex.disposables.CompositeDisposable;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Android on 2018/4/4.
 */

public class BaseActivity <T extends BasePresenter> extends SwipeBackActivity {

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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mDisposable != null) mDisposable.clear();
    }
}
