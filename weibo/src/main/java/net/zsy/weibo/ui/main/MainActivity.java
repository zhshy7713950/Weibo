package net.zsy.weibo.ui.main;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import net.zsy.weibo.R;
import net.zsy.weibo.bean.SnackbarBean;
import net.zsy.weibo.bean.WeiboTypeBean;
import net.zsy.weibo.bean.login.WbLoginHelper;
import net.zsy.weibo.ui.base.BaseActivity;
import net.zsy.weibo.ui.login.OAuth2Web;
import net.zsy.weibo.util.FragmentUtils;
import net.zsy.weibo.util.UIUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.SwipeBackLayout;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_frame_container)
    FrameLayout mainFrameContainer;
    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;
    @BindView(R.id.fab_top)
    FloatingActionButton fabTop;
    @BindView(R.id.fab_menu)
    FloatingActionsMenu fabMenu;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSwipeBackLayout().setEdgeTrackingEnabled(SwipeBackLayout.EDGE_RIGHT);

        new MainPresenter(this);
        presenter.initialDisplay();
    }


    @OnClick({R.id.fab_add, R.id.fab_top})
    public void onViewClicked(View view) {
        fabMenu.collapse();
        switch (view.getId()) {
            case R.id.fab_add:
                break;
            case R.id.fab_top:
                break;
        }
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void showWeiboView(WeiboTypeBean weiboTypeBean) {
        if (WbLoginHelper.getHelper().isLogin()) {
            presenter.getUserInfo();
        } else {
            FragmentUtils.replaceFragment(getFragmentManager(), new OAuth2Web(), R.id.main_frame_container, null);
        }
    }

    @Override
    public void getUserInfo(String userInfo) {
        showSnackbar(SnackbarBean.create().setMessage(userInfo).setDuration(Snackbar.LENGTH_SHORT));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showSnackbar(SnackbarBean snackbarBean) {
        UIUtils.showSnackBar(coordinatorLayout,snackbarBean);
    }
}
