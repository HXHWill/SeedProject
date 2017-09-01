package com.carlisle.seed.module.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;

import com.carlisle.framework.BaseActivity;
import com.carlisle.framework.FragmentAdapter;
import com.carlisle.seed.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Creator      : carlisle
 * Date         : 01/09/2017
 * Description  :
 */

public class SplashActivity extends BaseActivity {

    private ViewPager viewPager;
    private FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


        List<Fragment> fragments = new ArrayList<>();
        fragments.add(GuideFragment.newInstance(0));
        fragments.add(GuideFragment.newInstance(1));
        fragments.add(GuideFragment.newInstance(2));

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setAdapter(fragmentAdapter);
    }
}
