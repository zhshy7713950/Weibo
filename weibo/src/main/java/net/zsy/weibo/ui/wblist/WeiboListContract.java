package net.zsy.weibo.ui.wblist;

import net.zsy.weibo.ui.base.BasePresenter;
import net.zsy.weibo.ui.base.BaseView;

public interface WeiboListContract {

    interface Presenter extends BasePresenter {

        void getWeiboList(int page,int count);
    }

    interface View extends BaseView<Presenter> {

    }

}
