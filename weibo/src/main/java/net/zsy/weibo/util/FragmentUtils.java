package net.zsy.weibo.util;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

/**
 * Created by Android on 2018/4/10.
 */

public class FragmentUtils {

    public static void replaceFragment(FragmentManager fragmentManager, Fragment fragment, int containerId){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId, fragment, fragment.getClass().getName())
                .addToBackStack(fragment.getClass().getName())
                .commitAllowingStateLoss();
    }

    public static void addFragment(FragmentManager fragmentManager, Fragment fragment,int containerId){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(containerId, fragment, fragment.getClass().getName())
                .addToBackStack(fragment.getClass().getName())
                .commitAllowingStateLoss();
    }
}
