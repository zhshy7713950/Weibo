package net.zsy.weibo.util;

import android.graphics.drawable.GradientDrawable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;

import net.zsy.weibo.R;
import net.zsy.weibo.bean.SnackbarBean;

/**
 * Created by Android on 2018/4/9.
 */

public class UIUtils {

    public static void showSnackBar(View coordinatorLayout,SnackbarBean snackbarBean){
        Snackbar snackbar = Snackbar.make(coordinatorLayout, snackbarBean.getMessage(), snackbarBean.getDuration());
        if(!TextUtils.isEmpty(snackbarBean.getAction1())){
            snackbar.setAction(snackbarBean.getAction1(),snackbarBean.getOnClickListener1());
        }
        if(snackbarBean.getActionId1() != -1){
            snackbar.setAction(snackbarBean.getActionId1(),snackbarBean.getOnClickListener1());
        }
        if(!TextUtils.isEmpty(snackbarBean.getAction2())){
            snackbar.setAction(snackbarBean.getAction2(),snackbarBean.getOnClickListener2());
        }
        if(snackbarBean.getActionId2() != -1){
            snackbar.setAction(snackbarBean.getActionId2(),snackbarBean.getOnClickListener2());
        }
        snackbar.show();
    }

    public static int getWindowWidth(Window window) {
        DisplayMetrics metrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return metrics.widthPixels;
    }

    public static int getWindowHeight(Window window) {
        DisplayMetrics metrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return metrics.heightPixels;
    }
}
