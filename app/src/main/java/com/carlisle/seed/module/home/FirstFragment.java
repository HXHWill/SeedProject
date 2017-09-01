package com.carlisle.seed.module.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.carlisle.framework.LazyFragment;
import com.carlisle.seed.R;
import com.carlisle.seed.module.home.http.GithubApi;
import com.carlisle.seed.module.home.model.GithubBean;
import com.carlisle.seed.module.login.LoginActivity;
import com.carlisle.seed.provider.db.UserDao;
import com.carlisle.seed.provider.http.ApiFactory;
import com.carlisle.seed.provider.http.Domain;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Creator      : carlisle
 * Date         : 29/08/2017
 * Description  :
 */

public class FirstFragment extends LazyFragment {

    private EditText contentView;
    private ImageView avatarView;
    private TextView usernameView;
    private Button searchButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contentView = (EditText) view.findViewById(R.id.et_content);
        avatarView = (ImageView) view.findViewById(R.id.iv_avatar);
        usernameView = (TextView) view.findViewById(R.id.tv_username);
        searchButton = (Button) view.findViewById(R.id.btn_search);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchUserInfo(contentView.getText().toString().trim());
            }
        });

        view.findViewById(R.id.tv_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
    }

    private void searchUserInfo(String username) {
        ApiFactory.getInstance().create(Domain.DomainType.GITHUB, GithubApi.class)
                .getUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GithubBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GithubBean value) {
                        LogUtils.d("fetch onNext: " + value.toString());
                        usernameView.setText(value.getLogin());
                        Glide.with(getActivity())
                                .load(value.getAvatarUrl())
                                .transition(new DrawableTransitionOptions().dontTransition())
                                .into(avatarView);

                        UserDao.save(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d("fetch onError");
                        ToastUtils.showShort("nothing");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
