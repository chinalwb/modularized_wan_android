package com.chinalwb.wanandroid.base;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "http://www.wanandroid.com";
    private static Retrofit sRetrofit;

    public synchronized static Retrofit getRetrofit() {
        if (sRetrofit == null) {
            initInstance();
        }

        return sRetrofit;
    }

    private synchronized static void initInstance() {
        sRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
