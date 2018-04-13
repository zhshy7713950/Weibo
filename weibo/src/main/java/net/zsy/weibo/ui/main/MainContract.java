package net.zsy.weibo.ui.main;

import net.zsy.weibo.bean.WeiboTypeBean;
import net.zsy.weibo.ui.base.BasePresenter;
import net.zsy.weibo.ui.base.BaseView;

/**
 * Created by Android on 2018/4/10.
 */

public interface MainContract {

    interface Presenter extends BasePresenter{

        void initialDisplay();

        void getUserInfo();
    }

    interface View extends BaseView<Presenter>{

        void showWeiboView(WeiboTypeBean weiboTypeBean);

        void getUserInfo(String userInfo);
    }

}
