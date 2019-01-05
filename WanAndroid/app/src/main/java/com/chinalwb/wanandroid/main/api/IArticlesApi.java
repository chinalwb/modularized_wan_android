package com.chinalwb.wanandroid.main.api;

import com.chinalwb.wanandroid_base.features.article.model.ArticlesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IArticlesApi {

    @GET("article/list/{page}/json")
    Call<ArticlesResponse> listArticles(@Path("page") int page);

}
