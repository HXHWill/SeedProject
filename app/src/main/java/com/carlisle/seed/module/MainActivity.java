package com.carlisle.seed.module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.carlisle.framework.BaseActivity;
import com.carlisle.framework.FragmentAdapter;
import com.carlisle.seed.R;
import com.carlisle.seed.module.base.CommonActivity;
import com.carlisle.seed.module.home.HomeFragment;
import com.carlisle.seed.module.search.SearchFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Creator      : carlisle
 * Date         : 29/08/2017
 * Description  :
 */

public class MainActivity extends CommonActivity {

    private static final int[] TAB_ICONS = {R.drawable.selector_first, R.drawable.selector_second};
    private static final String[] TAB_TITLES = {"search", "history"};

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private FragmentAdapter fragmentAdapter;

    public static Intent buildIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new SearchFragment());

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < fragments.size(); i++) {
            View tabView = LayoutInflater.from(this).inflate(R.layout.view_custom_tab, null);
            ((ImageView) tabView.findViewById(R.id.iv_tab_icon)).setImageResource(TAB_ICONS[i]);
            ((TextView) tabView.findViewById(R.id.tv_tab_title)).setText(TAB_TITLES[i]);
            tabLayout.getTabAt(i).setCustomView(tabView);
        }
    }
}
