package com.carlisle.seed.module.home;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.carlisle.seed.R;
import com.carlisle.seed.module.home.model.GithubBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Creator      : carlisle
 * Date         : 31/08/2017
 * Description  :
 */

public class SecondAdapter extends BaseQuickAdapter<GithubBean, BaseViewHolder> {

    public SecondAdapter(@Nullable List data) {
        super(R.layout.item_search, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GithubBean item) {
        helper.setText(R.id.tv_username, item.getLogin());

        Glide.with(helper.itemView.getContext())
                .load(item.getAvatarUrl())
                .transition(new DrawableTransitionOptions().dontTransition())
                .into((ImageView) helper.getView(R.id.iv_avatar));
    }
}
