package com.chinalwb.wanandroid.features.wx.api;

import com.chinalwb.wanandroid.features.wx.model.GzhTab;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IGzhApi {

    @GET("wxarticle/chapters/json")
    Call<GzhTab> loadGzhTabs();
}
