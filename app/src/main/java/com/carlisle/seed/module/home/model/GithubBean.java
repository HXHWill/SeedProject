package com.carlisle.seed.module.home.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import io.realm.GithubBeanRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Creator      : carlisle
 * Date         : 23/06/2017
 * Description  :
 */
@Parcel(implementations = {GithubBeanRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {GithubBean.class})
public class GithubBean extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    private Integer userId;
    private String login;
    @SerializedName("avatar_url")
    private String avatarUrl;

    public GithubBean() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer id) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "GithubBean{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
