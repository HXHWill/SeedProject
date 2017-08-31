package com.carlisle.seed;

import com.blankj.utilcode.util.Utils;
import com.carlisle.framework.swipeback.common.SwipeBackApplication;
import com.carlisle.seed.provider.db.RealmUtil;

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
        RealmUtil.init(this);
    }
}
