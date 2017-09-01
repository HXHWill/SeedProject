package com.carlisle.seed.module.search.http;

import com.carlisle.seed.module.search.model.GithubBean;

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
