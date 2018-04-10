package net.zsy.weibo.ui.base;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Android on 2018/4/4.
 */

public class BaseActivity<T extends BasePresenter> extends SwipeBackActivity {

    protected T presenter;

    @Override
    protected void onResume() {
        super.onResume();
        if(presenter != null) presenter.attachView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(presenter != null) presenter.detachView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
