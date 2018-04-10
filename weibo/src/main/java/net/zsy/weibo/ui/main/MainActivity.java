package net.zsy.weibo.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import net.zsy.weibo.R;
import net.zsy.weibo.ui.base.BaseActivity;
import net.zsy.weibo.ui.login.OAuth2Web;
import net.zsy.weibo.util.FragmentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.SwipeBackLayout;

public class MainActivity extends BaseActivity <MainContract.Presenter> implements MainContract.View{

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
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
                Intent intent = new Intent(MainActivity.this, OAuth2Web.class);
                startActivity(intent);
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
    public void showLoginView() {
        FragmentUtils.replaceFragment(getFragmentManager(),new OAuth2Web(),R.id.main_frame_container);
    }

    @Override
    public void showWeiboView() {

    }

    public void showSnakeBar(String msg){

    }
}
