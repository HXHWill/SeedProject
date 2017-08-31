package com.carlisle.seed.module;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.carlisle.framework.BaseActivity;
import com.carlisle.seed.R;

/**
 * Creator      : carlisle
 * Date         : 29/08/2017
 * Description  :
 */

public class BottomTabActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tab);
    }
}
