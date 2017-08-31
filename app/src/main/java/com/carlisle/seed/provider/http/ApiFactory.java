package com.carlisle.seed.provider.http;

import com.carlisle.seed.BuildConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Creator      : carlisle
 * Date         : 09/06/2017
 * Description  :
 */

public class ApiFactory {

    private volatile static ApiFactory instance;
    private Map<Domain.DomainType, Object> apis;

    private Converter.Factory converterFactory = GsonConverterFactory.create();

    private ApiFactory() {
        apis = new HashMap<>();
    }

    public static ApiFactory getInstance() {
        if (instance == null) {
            synchronized (ApiFactory.class) {
                if (instance == null) {
                    instance = new ApiFactory();
                }
            }
        }
        return instance;
    }

    public void clearCache() {
        apis.clear();
    }

    public <T> T create(Domain.DomainType serverDomainType, Class<T> tClass) {
        return create(serverDomainType, tClass, converterFactory);
    }

    public <T> T create(Domain.DomainType domainType, Class<T> tClass, Converter.Factory converterFactory) {
        T api = (T) apis.get(domainType);
        if (api == null) {
            api = new Retrofit.Builder()
                    .client(addClient())
                    .baseUrl(Domain.get(domainType))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(converterFactory)
                    .build()
                    .create(tClass);

            apis.put(domainType, api);
        }
        return api;
    }

    private OkHttpClient addClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .addNetworkInterceptor(new HeaderInterceptor())
                .addInterceptor(addTokenInterceptor())
                .retryOnConnectionFailure(true)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        return builder.build();
    }

    public Interceptor addTokenInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url;
                if ((original.url().url().getHost().contains("domain"))) {
                    url = originalHttpUrl.newBuilder()
                            .addQueryParameter("token", "token")
                            .build();
                } else {
                    url = originalHttpUrl.newBuilder()
                            .build();
                }

                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
    }

    public class HeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request.Builder builder = chain.request().newBuilder();
            builder.header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
            builder.header("Accept-Language", "zh-CN,zh;q=0.8,ja;q=0.6,zh-TW;q=0.4,en;q=0.2,vi;q=0.2");

            return chain.proceed(builder.build());
        }
    }
}
