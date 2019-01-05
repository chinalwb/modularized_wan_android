package com.chinalwb.wanandroid_projects.presenter;

import com.chinalwb.wanandroid_base.features.article.model.Article;
import com.chinalwb.wanandroid_base.features.article.model.ArticlesResponse;
import com.chinalwb.wanandroid_projects.api.IProjectsApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectsPresenter implements ProjectsContract.Presenter {

    private IProjectsApi api;
    private int currentPage;
    private ProjectsContract.View view;
    private boolean isLoading;

    public ProjectsPresenter(IProjectsApi api, ProjectsContract.View view) {
        this.api = api;
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void loadProjects(int page) {
        this.toggleLoading(true);
        this.currentPage = page;
        Call<ArticlesResponse> listProjectsCall = api.listProjects(page);
        listProjectsCall.enqueue(new Callback<ArticlesResponse>() {
            @Override
            public void onResponse(Call<ArticlesResponse> call, Response<ArticlesResponse>
                    response) {
                List<Article> projectList = response.body().getData().getDatas();
                view.showProjects(projectList);
                toggleLoading(false);
            }

            @Override
            public void onFailure(Call<ArticlesResponse> call, Throwable t) {
                view.showError(t);
                toggleLoading(false);
            }
        });
    }

    @Override
    public void loadNextPageProjects() {
        this.toggleLoading(true);
        this.currentPage += 1;
        Call<ArticlesResponse> nextProjectsCall = api.listProjects(this.currentPage);
        nextProjectsCall.enqueue(new Callback<ArticlesResponse>() {
            @Override
            public void onResponse(Call<ArticlesResponse> call, Response<ArticlesResponse>
                    response) {
                List<Article> projectsList = response.body().getData().getDatas();
                view.appendProjects(projectsList);
                toggleLoading(false);
            }

            @Override
            public void onFailure(Call<ArticlesResponse> call, Throwable t) {
                view.showError(t);
                toggleLoading(false);
            }
        });
    }

    @Override
    public void refreshProjects() {
        this.loadProjects(0);
    }

    @Override
    public void start() {
        this.loadProjects(0);
    }

    private void toggleLoading(boolean loading) {
        this.isLoading = loading;
        if (this.isLoading) {
            view.showLoading();
        } else {
            view.hideLoading();
        }
    }
}
