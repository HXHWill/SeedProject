package com.carlisle.framework;

import android.content.Context;
import android.os.Handler;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.carlisle.framework.swipeback.dispatchactivity.SwipeBackActivity;

/**
 * Creator      : carlisle
 * Date         : 28/08/2017
 * Description  :
 */

public class BaseActivity extends SwipeBackActivity {

    protected boolean isStatusBarEnabled = true;
    protected boolean isDoubleBackExitEnabled = false;
    private boolean allowExit = true;
    //按下返回键次数
    private int backPressedCount = 0;

    public void setDoubleBackExitEnabled(boolean doubleBackExitEnabled) {
        isDoubleBackExitEnabled = doubleBackExitEnabled;
    }

    public void setAllowExit(boolean allow) {
        allowExit = allow;
    }

    protected boolean isStatusBarEnabled() {
        return isStatusBarEnabled;
    }

    protected void toogleStatus() {
        showStatus(!isStatusBarEnabled);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (!allowExit) {
            return;
        }

        if (isDoubleBackExitEnabled) {
            handleBack();
        } else {
            finish();
        }
    }

    protected void handleBack() {
        hideSoftKeyboard();

        backPressedCount++;
        if (backPressedCount == 1) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
        } else if (backPressedCount >= 2) {
            finish();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backPressedCount = 0;
            }
        }, 10000);
    }

    protected void showStatus(boolean enabled) {
        isStatusBarEnabled = enabled;
        if (enabled) {
            WindowManager.LayoutParams attr = getWindow().getAttributes();
            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attr);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            WindowManager.LayoutParams attr = getWindow().getAttributes();
            attr.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(attr);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            final InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void showSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
    }
}
