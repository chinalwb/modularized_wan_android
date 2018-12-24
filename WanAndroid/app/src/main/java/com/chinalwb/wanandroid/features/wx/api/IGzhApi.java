package com.chinalwb.wanandroid.features.wx.api;

import com.chinalwb.wanandroid.features.wx.model.GzhListResponse;
import com.chinalwb.wanandroid.features.wx.model.GzhTab;
import com.chinalwb.wanandroid.main.model.ArticlesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IGzhApi {

    @GET("wxarticle/chapters/json")
    Call<GzhListResponse> loadGzhTabs();

    @GET("wxarticle/list/{gzhId}/{page}/json")
    Call<ArticlesResponse> loadGzhArticles(@Path("gzhId") int gzhId, @Path("page") int page);
}
