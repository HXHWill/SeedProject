package com.carlisle.seed.module.base;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.WindowManager;

import com.carlisle.framework.BaseActivity;
import com.carlisle.seed.R;

/**
 * Creator      : carlisle
 * Date         : 02/09/2017
 * Description  :
 */

public class CommonActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setBackgroundDrawableResource(R.color.colorPrimaryDark);
            getWindow().setStatusBarColor(getColor(R.color.transparent));
        }
    }

}
