package com.carlisle.seed.module.home.http;

import com.carlisle.seed.module.home.model.GithubBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Creator      : carlisle
 * Date         : 31/08/2017
 * Description  :
 */

public interface GithubApi {
    @GET("/users/{username}")
    Observable<GithubBean> getUser(@Path("username") String username);
}
