package com.carlisle.framework;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Creator      : carlisle
 * Date         : 28/08/2017
 * Description  :
 */

public abstract class LazyFragment extends BaseFragment {

    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;
    private boolean isVisible = false;
    private List<FragmentVisibleListener> fragmentVisibleListeners = new ArrayList<>();


    private FragmentVisibleListener listener = new FragmentVisibleListener() {
        @Override
        public void onVisibleChanged(boolean isVisible) {
            setVisibleToUser(isVisible);
        }
    };

    final void addVisibleListener(FragmentVisibleListener listener) {
        if (!fragmentVisibleListeners.contains(listener)) {
            fragmentVisibleListeners.add(listener);
        }
    }

    final void removeVisibleListener(FragmentVisibleListener listener) {
        fragmentVisibleListeners.remove(listener);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initParentVisibleListener();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        removeParentVisibleListener();
    }

    private void initParentVisibleListener() {
        Fragment fragment = getParentFragment();
        if (fragment != null && fragment instanceof LazyFragment) {
            ((LazyFragment) fragment).addVisibleListener(listener);
        }
    }

    private void removeParentVisibleListener() {
        Fragment fragment = getParentFragment();
        if (fragment != null && fragment instanceof LazyFragment) {
            ((LazyFragment) fragment).removeVisibleListener(listener);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        setVisibleToUser(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        setVisibleToUser(false);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        setVisibleToUser(isVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        setVisibleToUser(!hidden);
    }

    private boolean checkVisible() {
        Fragment fragment = getParentFragment();
        while (fragment != null) {
            if (fragment.isHidden() || !fragment.getUserVisibleHint()) {
                return false;
            }
            fragment = fragment.getParentFragment();
        }
        return !isHidden() && getUserVisibleHint();
    }

    public void setVisibleToUser(boolean isVisibleToUser) {
        if (getView() != null) {
            if (isVisibleToUser) {
                if (!isVisible && checkVisible()) {
                    if (isFirstVisible) {
                        isFirstVisible = false;
                        onFirstUserVisible();
                    } else {
                        onUserVisible();
                    }
                    isVisible = isVisibleToUser;
                    notifyFragmentVisibleListeners(isVisibleToUser);
                }
            } else {
                if (isVisible) {
                    if (isFirstInvisible) {
                        isFirstInvisible = false;
                        onFirstUserInvisible();
                    } else {
                        onUserInvisible();
                    }
                    notifyFragmentVisibleListeners(isVisibleToUser);
                }
                isVisible = isVisibleToUser;
            }
        }
    }

    private void notifyFragmentVisibleListeners(boolean isVisible) {
        for (FragmentVisibleListener listener : fragmentVisibleListeners) {
            listener.onVisibleChanged(isVisible);
        }
    }

    protected void onFirstUserVisible() {
        onUserVisible();
    }

    protected void onUserVisible() {

    }

    protected void onFirstUserInvisible() {
        onUserInvisible();
    }

    protected void onUserInvisible() {

    }

    private interface FragmentVisibleListener {
        void onVisibleChanged(boolean isVisible);
    }
}
