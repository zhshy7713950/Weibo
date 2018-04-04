package net.zsy.weibo.ui.main;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import net.zsy.weibo.R;
import net.zsy.weibo.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

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
}
