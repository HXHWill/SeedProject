package com.carlisle.seed.provider.db;

import com.blankj.utilcode.util.LogUtils;
import com.carlisle.seed.module.search.model.GithubBean;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Creator      : carlisle
 * Date         : 23/06/2017
 * Description  :
 */

public class UserDao {
    private static final String TAG = "UserDao";

    public static void save(final GithubBean item) {
        LogUtils.d(TAG, "save");

        RealmUtil.getRealmInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(item);
            }
        });
    }

    public static void remove() {
        LogUtils.d(TAG, "remove");

        RealmUtil.getRealmInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(GithubBean.class).findAll().deleteAllFromRealm();
            }
        });
    }

    public static RealmResults<GithubBean> getAll() {
        LogUtils.d(TAG, "getAll");
        return RealmUtil.getRealmInstance()
                .where(GithubBean.class)
                .findAll();
    }
}
