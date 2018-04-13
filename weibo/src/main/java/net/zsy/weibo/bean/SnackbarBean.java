package net.zsy.weibo.bean;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;

public class SnackbarBean {

    @NonNull
    private String message;
    private String action1;
    private String action2;
    private int actionId1 = -1;
    private int actionId2 = -1;
    private View.OnClickListener onClickListener1;
    private View.OnClickListener onClickListener2;
    private int duration = Snackbar.LENGTH_SHORT;

    public int getActionId1() {
        return actionId1;
    }

    public SnackbarBean setActionId1(int actionId1) {
        this.actionId1 = actionId1;
        return this;
    }

    public int getActionId2() {
        return actionId2;
    }

    public SnackbarBean setActionId2(int actionId2) {
        this.actionId2 = actionId2;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public SnackbarBean setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public String getAction1() {
        return action1;
    }

    public SnackbarBean setAction1(String action1) {
        this.action1 = action1;
        return this;
    }

    public String getAction2() {
        return action2;
    }

    public SnackbarBean setAction2(String action2) {
        this.action2 = action2;
        return this;
    }

    public View.OnClickListener getOnClickListener1() {
        return onClickListener1;
    }

    public SnackbarBean setOnClickListener1(View.OnClickListener onClickListener1) {
        this.onClickListener1 = onClickListener1;
        return this;
    }

    public View.OnClickListener getOnClickListener2() {
        return onClickListener2;
    }

    public SnackbarBean setOnClickListener2(View.OnClickListener onClickListener2) {
        this.onClickListener2 = onClickListener2;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public SnackbarBean setMessage(String message) {
        this.message = message;
        return this;
    }

    public static SnackbarBean create(){
        return new SnackbarBean();
    }
}
