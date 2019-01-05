package com.chinalwb.wanandroid_projects.api;

import com.chinalwb.wanandroid_base.features.article.model.ArticlesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IProjectsApi {

    @GET("article/listproject/{page}/json")
    Call<ArticlesResponse> listProjects(@Path("page") int page);
}
