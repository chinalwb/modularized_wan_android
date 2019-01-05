package com.chinalwb.wanandroid_gzh.api;

import com.chinalwb.wanandroid_base.features.article.model.ArticlesResponse;
import com.chinalwb.wanandroid_gzh.model.GzhListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IGzhApi {

    @GET("wxarticle/chapters/json")
    Call<GzhListResponse> loadGzhTabs();

    @GET("wxarticle/list/{gzhId}/{page}/json")
    Call<ArticlesResponse> loadGzhArticles(@Path("gzhId") int gzhId, @Path("page") int page);
}
