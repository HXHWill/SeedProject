package com.carlisle.seed.module.home;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.carlisle.seed.R;
import com.carlisle.seed.module.login.LoginActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

/**
 * Creator      : carlisle
 * Date         : 31/08/2017
 * Description  :
 */

public class HomeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public HomeAdapter() {
        super(R.layout.item_home_item, new ArrayList<String>());
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        final Context context = helper.itemView.getContext();

        Glide.with(context)
                .load(item)
                .apply(new RequestOptions()
                        .override(ScreenUtils.getScreenWidth(), 80))
                .transition(new DrawableTransitionOptions().dontTransition())
                .into((ImageView) helper.getView(R.id.iv_cover));

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(LoginActivity.buildIntent(context));
            }
        });
    }
}
