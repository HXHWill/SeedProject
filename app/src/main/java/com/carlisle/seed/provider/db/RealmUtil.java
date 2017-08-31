package com.carlisle.seed.provider.db;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by carlisle on 11/22/16.
 */

public class RealmUtil {
    static RealmConfiguration siteConfig;

    public static Realm getRealmInstance() {
        return Realm.getInstance(siteConfig);
    }

    public static void init(Context context) {
        init(context, "visitor");
    }

    public static void init(Context context, String username) {
        Realm.init(context);

        StringBuilder dbNameBuilder = new StringBuilder();

        dbNameBuilder.append("realm_")
                .append(username)
                .append(".realm");

        siteConfig = new RealmConfiguration.Builder()
                .name(dbNameBuilder.toString())
                .build();
    }
}
