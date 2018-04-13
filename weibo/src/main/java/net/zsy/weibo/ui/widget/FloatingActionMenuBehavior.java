package net.zsy.weibo.ui.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.List;

@SuppressWarnings("unused")
public class FloatingActionMenuBehavior extends CoordinatorLayout.Behavior<FloatingActionsMenu> {


    public FloatingActionMenuBehavior() {
    }

    public FloatingActionMenuBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionsMenu child, View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionsMenu child, View dependency) {
        float TranslationY = getFabTranslationYForSnackbar(parent,child);
        if(TranslationY != 0){
            ViewCompat.offsetTopAndBottom(child, (int) TranslationY);
        }

        return false;
    }

    private float getFabTranslationYForSnackbar(CoordinatorLayout parent,
                                                FloatingActionsMenu fab) {
        float minOffset = 0;
        final List<View> dependencies = parent.getDependencies(fab);
        for (int i = 0, z = dependencies.size(); i < z; i++) {
            final View view = dependencies.get(i);
            if (view instanceof Snackbar.SnackbarLayout && parent.doViewsOverlap(fab, view)) {
                minOffset = Math.min(minOffset,
                        view.getTranslationY() - view.getHeight());
            }
        }
        return minOffset;
    }

}
