package net.zsy.weibo.util;

import android.util.Log;

public class Logger {

    private static final String TAG = "Weibo_Zsy";

    private Logger() {
    }

    public static void v(String msg) {
        if (isDebug())
            Log.v(TAG, buildMessage(msg));
    }

    public static void v(String msg, Throwable thr) {
        if (isDebug())
            Log.v(TAG, buildMessage(msg), thr);
    }

    public static void d(String msg) {
        if (isDebug())
            Log.d(TAG, buildMessage(msg));
    }

    public static void d(String msg, Throwable thr) {
        if (isDebug())
            Log.d(TAG, buildMessage(msg), thr);
    }

    public static void i(String msg) {
        Log.i(TAG, buildMessage(msg));
    }

    public static void i(String msg, Throwable thr) {
        Log.i(TAG, buildMessage(msg), thr);
    }

    public static void w(String msg) {
        Log.w(TAG, buildMessage(msg));
    }

    public static void w(String msg, Throwable thr) {
        Log.w(TAG, buildMessage(msg), thr);
    }

    public static void w(Throwable thr) {
        Log.w(TAG, buildMessage("warining"), thr);
    }

    public static void e(String msg) {
        Log.e(TAG, buildMessage(msg));
    }

    public static void e(Throwable thr) {
        Log.e(TAG, buildMessage("error"), thr);
    }


    public static void e(String msg, Throwable thr) {
        Log.e(TAG, buildMessage(msg), thr);
    }

    protected static String buildMessage(String msg) {
        StackTraceElement caller = new Throwable().fillInStackTrace().getStackTrace()[2];
        return caller.getFileName()
                + "." + caller.getMethodName()
                + "(): " + msg;
    }

    public static boolean isDebug() {
        return WeiboUtil.DEBUG;
    }

}
