package com.carlisle.framework;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Creator      : carlisle
 * Date         : 28/08/2017
 * Description  :
 */

public class BaseFragment extends Fragment {
    protected void hideSoftKeyboard() {
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            ((BaseActivity) activity).hideSoftKeyboard();
        }
    }
}
