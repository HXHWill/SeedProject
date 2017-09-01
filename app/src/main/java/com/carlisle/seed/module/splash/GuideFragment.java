package com.carlisle.seed.module.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.carlisle.framework.BaseFragment;
import com.carlisle.seed.R;
import com.carlisle.seed.module.MainActivity;

/**
 * Creator      : carlisle
 * Date         : 01/09/2017
 * Description  :
 */

public class GuideFragment extends BaseFragment {

    private static final String BUNDLE_INDEX = "bundle_index";
    private static final int[] RESOURCE_IDS = {R.drawable.guide, R.drawable.guide, R.drawable.guide};

    private ImageView contentView;
    private TextView skipView;

    public static GuideFragment newInstance(int index) {
        GuideFragment fragment = new GuideFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_guide, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int index = getArguments().getInt(BUNDLE_INDEX);

        contentView = (ImageView) view.findViewById(R.id.iv_content);
        contentView.setImageResource(RESOURCE_IDS[index]);

        skipView = (TextView) view.findViewById(R.id.tv_skip);
        skipView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.buildIntent(getActivity()));
                getActivity().finish();
            }
        });

        if (index == RESOURCE_IDS.length - 1) {
            skipView.setVisibility(View.VISIBLE);
        } else {
            skipView.setVisibility(View.GONE);
        }
    }
}
