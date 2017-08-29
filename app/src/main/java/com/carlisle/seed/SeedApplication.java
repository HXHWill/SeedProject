package com.carlisle.seed;

import com.blankj.utilcode.util.Utils;
import com.carlisle.framework.swipeback.common.SwipeBackApplication;

/**
 * Creator      : carlisle
 * Date         : 28/08/2017
 * Description  :
 */

public class SeedApplication extends SwipeBackApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
