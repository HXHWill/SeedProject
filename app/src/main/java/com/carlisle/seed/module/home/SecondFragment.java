package com.carlisle.seed.module.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carlisle.framework.LazyFragment;
import com.carlisle.seed.R;
import com.carlisle.seed.provider.db.UserDao;

/**
 * Creator      : carlisle
 * Date         : 29/08/2017
 * Description  :
 */

public class SecondFragment extends LazyFragment {

    private RecyclerView recyclerView;
    private SecondAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        adapter = new SecondAdapter(UserDao.getAll());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onUserVisible() {
        super.onUserVisible();
    }

}
