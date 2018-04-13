package net.zsy.weibo.ui;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;

import net.zsy.weibo.data.WeiboDataManager;
import net.zsy.weibo.data.async.AsyncDataManager;
import net.zsy.weibo.data.disk.DiskDataManager;
import net.zsy.weibo.util.WeiboUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.Stack;

/**
 * Created by Android on 2018/4/4.
 */

public class WeiboApplication extends Application {

    private static Context ctx;
    private static Stack<Activity> activityStack;

    @Override
    public void onCreate() {
        super.onCreate();
        ctx = getApplicationContext();

        initializeInThread();
        registerActivityListener();
    }

    private void initializeInThread() {
        new Thread(()->{
            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

            WbSdk.install(ctx,new AuthInfo(this, WeiboUtil.SINA_APP_KEY, WeiboUtil.SINA_REDIRECT_URI, WeiboUtil.SINA_SCOPE));

            WeiboDataManager.getInstance().initialize(AsyncDataManager.getInstance(), DiskDataManager.getInstance());
        }).start();
    }

    private void registerActivityListener() {
        activityStack = new Stack<>();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                pushActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) { }

            @Override
            public void onActivityResumed(Activity activity) {
                if (!EventBus.getDefault().isRegistered(activity))
                    EventBus.getDefault().register(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {
                EventBus.getDefault().unregister(activity);
            }

            @Override
            public void onActivityStopped(Activity activity) { }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) { }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if (null == activityStack || activityStack.isEmpty()){
                    return;
                }
                popActivity(activity);
            }
        });
    }

    public static void AppExit(Context context) {
        try {
            finishAllActivity();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 结束所有Activity
     */
    public static void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }
    /**
     * 结束其他activity除了指定的一个class
     */
    public static void finishAllActivityExceptOne(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (!activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束其他activity除了指定的一个
     */
    public static void finishAllActivityExceptOne(Activity activity) {
        for (Activity a : activityStack) {
            if(a != activity){
                finishActivity(a);
            }
        }
    }

    /**
     * 结束指定的Activity
     */
    public static void finishActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            popActivity(activity);
        }
    }
    /**
     * 结束指定类名的Activity
     */
    public static void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                activity.finish();
                popActivity(activity);
            }
        }
    }

    private static void pushActivity(Activity activity){
        activityStack.push(activity);
    }

    private static void popActivity(Activity activity){
        activityStack.remove(activity);
    }

    public static Context getAppContext(){
        return ctx;
    }
}
