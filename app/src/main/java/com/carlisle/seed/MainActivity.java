package com.carlisle.seed;

import android.os.Bundle;

import com.blankj.utilcode.util.FragmentUtils;
import com.carlisle.framework.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setDoubleBackExitEnabled(true);

        FragmentUtils.addFragment(getSupportFragmentManager(), new MainFragment(), R.id.rl_container);
    }
}
