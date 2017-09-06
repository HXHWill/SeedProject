package com.carlisle.seed.provider.db;

import android.content.Context;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by carlisle on 11/22/16.
 */

public class RealmUtil {
    private static RealmConfiguration defaultConfig;

    public static RealmMigration migration = new RealmMigration() {//升级数据库
        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
            // DynamicRealm exposes an editable schema
            RealmSchema schema = realm.getSchema();
            if (oldVersion > newVersion) {//数据库降级
                Realm.deleteRealm(defaultConfig);
            } else if (oldVersion < newVersion) {//数据库升级

            }
        }
    };

    public static Realm getRealmInstance() {
        return Realm.getInstance(defaultConfig);
    }

    public static void setup(Context context, String username, int dbVersion, Object dbModules) {
        Realm.init(context);

        StringBuilder dbNameBuilder = new StringBuilder();
        dbNameBuilder.append("spring_")
                .append(username)
                .append(".realm");

        defaultConfig = new RealmConfiguration.Builder()
                .schemaVersion(dbVersion)
                .migration(migration)
                .modules(dbModules) //只存指定modules的类
                .name(dbNameBuilder.toString())
                .build();
    }

    public static void deleteAllRealmObjects() {
        Realm realm = getRealmInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });
        realm.close();
    }
}
