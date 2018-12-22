package com.chinalwb.wanandroid.main.api;

import com.chinalwb.wanandroid.main.model.ArticlesList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IArticlesApi {

    @GET("article/list/{page}/json")
    Call<ArticlesList> listArticles(@Path("page") int page);
}
