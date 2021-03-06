package com.carlisle.seed.module.search;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.carlisle.seed.R;
import com.carlisle.seed.module.search.model.GithubBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Creator      : carlisle
 * Date         : 31/08/2017
 * Description  :
 */

public class SearchAdapter extends BaseQuickAdapter<GithubBean, BaseViewHolder> {

    public SearchAdapter(@Nullable List data) {
        super(R.layout.item_search, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GithubBean item) {
        helper.setText(R.id.tv_username, item.getLogin());

        Glide.with(helper.itemView.getContext())
                .load(item.getAvatarUrl())
                .into((ImageView) helper.getView(R.id.iv_avatar));
    }
}
