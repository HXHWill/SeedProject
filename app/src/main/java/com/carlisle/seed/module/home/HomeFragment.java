package com.carlisle.seed.module.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.carlisle.framework.LazyFragment;
import com.carlisle.seed.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Creator      : carlisle
 * Date         : 29/08/2017
 * Description  :
 */

public class HomeFragment extends LazyFragment {

    private Banner banner;
    private RecyclerView recyclerView;
    private HomeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        banner = (Banner) view.findViewById(R.id.banner);

        adapter = new HomeAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);

        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(getBannerUrls());
        banner.setBannerAnimation(Transformer.DepthPage);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setDelayTime(3000);
        banner.isAutoPlay(true);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtils.showShort(String.valueOf(position));
            }
        });
    }

    @Override
    protected void onUserVisible() {
        super.onUserVisible();
        adapter.setNewData(getBannerUrls());
        banner.start();
    }

    @Override
    protected void onUserInvisible() {
        super.onUserInvisible();
        banner.stopAutoPlay();
    }

    private List<String> getBannerUrls() {
        List<String> bannerUrls = new ArrayList<>();
        bannerUrls.add("http://wallions.com/uploads/post-thumbs/w1920/276832.jpg");
        bannerUrls.add("http://wallions.com/uploads/post-thumbs/w1920/276827.jpg");
        bannerUrls.add("http://wallions.com/uploads/post-thumbs/w1920/276792.jpg");
        return bannerUrls;
    }

}
