package com.carlisle.seed.module;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.carlisle.framework.LazyFragment;
import com.carlisle.seed.R;

/**
 * Creator      : carlisle
 * Date         : 29/08/2017
 * Description  :
 */

public class MainFragment extends LazyFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    protected void onUserVisible() {
        super.onUserVisible();
        LogUtils.d("onUserVisible");
    }

    @Override
    protected void onUserInvisible() {
        super.onUserInvisible();
        LogUtils.d("onUserVisible");
    }
}
