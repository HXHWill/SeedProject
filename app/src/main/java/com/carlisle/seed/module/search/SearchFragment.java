package com.carlisle.seed.module.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.carlisle.framework.LazyFragment;
import com.carlisle.seed.R;
import com.carlisle.seed.module.search.http.GithubApi;
import com.carlisle.seed.module.search.model.GithubBean;
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

public class SearchFragment extends LazyFragment {

    private EditText contentView;
    private Button searchButton;
    private RecyclerView recyclerView;
    private SearchAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        contentView = (EditText) view.findViewById(R.id.et_content);
        searchButton = (Button) view.findViewById(R.id.btn_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchUserInfo(contentView.getText().toString().trim());
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        adapter = new SearchAdapter(UserDao.getAll());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onUserVisible() {
        super.onUserVisible();
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
