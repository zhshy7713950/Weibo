package net.zsy.weibo.util;

import android.util.DisplayMetrics;
import android.view.Window;

/**
 * Created by Android on 2018/4/9.
 */

public class UIUtils {

    public static int getWindowWidth(Window window) {
        DisplayMetrics metrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return metrics.widthPixels;
    }
}
