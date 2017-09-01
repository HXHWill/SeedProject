package com.carlisle.seed;

import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.carlisle.framework.swipeback.common.SwipeBackApplication;
import com.carlisle.seed.provider.db.RealmUtil;
import com.google.gson.Gson;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Creator      : carlisle
 * Date         : 28/08/2017
 * Description  :
 */

public class SeedApplication extends SwipeBackApplication {
    public static final String APP_DIR_NAME = "Spring";
    private static final String TAG = "SpringApplication";

    private static Context instance;
    private static Gson gson;

    public static Context from() {
        return instance;
    }

    public static Gson getGson() {
        if (gson == null)
            gson = new Gson();
        return gson;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = getApplicationContext();

        long firstTime = System.currentTimeMillis();

        initRealm();
        initFontConfig();
        initUtils();

        LogUtils.d(TAG, "time:" + (System.currentTimeMillis() - firstTime));
    }

    private void initRealm() {
        RealmUtil.init(this);
    }

    private void initUtils() {
        Utils.init(this);
    }

    private void initFontConfig() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/BrandonTextRegular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
