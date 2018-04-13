package net.zsy.weibo.util;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Created by Android on 2018/4/10.
 */

public class FragmentUtils {

    public static void replaceFragment(FragmentManager fragmentManager, Fragment fragment, int containerId, Bundle bundle){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(bundle != null){
            fragment.setArguments(bundle);
        }
        transaction.replace(containerId, fragment, fragment.getClass().getName())
                .commitAllowingStateLoss();
    }

    public static void addFragment(FragmentManager fragmentManager, Fragment fragment,int containerId, Bundle bundle){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(bundle != null){
            fragment.setArguments(bundle);
        }
        transaction.add(containerId, fragment, fragment.getClass().getName())
                .commitAllowingStateLoss();
    }
}
