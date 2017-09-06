package com.carlisle.seed.provider.db;

import com.blankj.utilcode.util.LogUtils;
import com.carlisle.seed.module.search.model.GithubBean;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.carlisle.seed.provider.db.RealmUtil.getRealmInstance;

/**
 * Creator      : carlisle
 * Date         : 23/06/2017
 * Description  :
 */

public class GithubDao {
    private static final String TAG = "GithubDao";

    public static void add(final GithubBean item) {
        LogUtils.d(TAG, "add");

        Realm realm = getRealmInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(item);
            }
        });
        realm.close();
    }

    public static void delete(final GithubBean githubBean) {
        LogUtils.d(TAG, "delete");

        Realm realm = getRealmInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(GithubBean.class)
                        .equalTo("userId", githubBean.getUserId())
                        .findAll()
                        .deleteAllFromRealm();
            }
        });
        realm.close();
    }

    public static void deleteAll() {
        LogUtils.d(TAG, "deleteAll");

        Realm realm = getRealmInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(GithubBean.class);
            }
        });
        realm.close();
    }

    public static RealmResults<GithubBean> getAll() {
        LogUtils.d(TAG, "getAll");

        Realm realm = getRealmInstance();
        RealmResults<GithubBean> realmResults = realm.where(GithubBean.class).findAll();
        return realmResults;
    }
}
