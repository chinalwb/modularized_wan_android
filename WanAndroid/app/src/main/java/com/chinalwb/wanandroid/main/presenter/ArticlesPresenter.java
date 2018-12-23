package com.chinalwb.wanandroid.main.presenter;

import com.chinalwb.wanandroid.main.api.IArticlesApi;
import com.chinalwb.wanandroid.main.model.Article;
import com.chinalwb.wanandroid.main.model.ArticlesList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticlesPresenter implements ArticlesContract.Presenter {

    private IArticlesApi mApi;
    private ArticlesContract.View mArticlesView;
    private int currentPage;
    private boolean isLoading = false;

    public ArticlesPresenter(IArticlesApi api, ArticlesContract.View view) {
        this.mApi = api;
        this.mArticlesView = view;
        this.mArticlesView.setPresenter(this);
    }

    @Override
    public void loadArticles(int page) {
        this.toggleLoading(true);
        this.currentPage = page;
        Call<ArticlesList> call = mApi.listArticles(page);
        call.enqueue(new Callback<ArticlesList>() {
            @Override
            public void onResponse(Call<ArticlesList> call, Response<ArticlesList> response) {
                List<Article> articleList = response.body().getData().getDatas();
                mArticlesView.showArticles(articleList);
                toggleLoading(false);
            }

            @Override
            public void onFailure(Call<ArticlesList> call, Throwable t) {
                mArticlesView.showError("Unknown");
                toggleLoading(false);
            }
        });
    }

    @Override
    public void loadNextPageArticles() {
        if (isLoading) {
            return;
        }
        this.toggleLoading(true);
        this.currentPage++;
        Call<ArticlesList> call = mApi.listArticles(this.currentPage);
        call.enqueue(new Callback<ArticlesList>() {
            @Override
            public void onResponse(Call<ArticlesList> call, Response<ArticlesList> response) {
                List<Article> articleList = response.body().getData().getDatas();
                mArticlesView.appendArticles(articleList);
                toggleLoading(false);
            }

            @Override
            public void onFailure(Call<ArticlesList> call, Throwable t) {
                mArticlesView.showError("Unknown");
                toggleLoading(false);
            }
        });
    }

    @Override
    public void refreshArticles() {
        loadArticles(0);
    }

    @Override
    public void start() {
        loadArticles(0);
    }

    private void toggleLoading(boolean isLoading) {
        this.isLoading = isLoading;
        if (isLoading) {
            this.mArticlesView.showLoading();
        } else {
            this.mArticlesView.hideLoading();
        }
    }
}
