package com.chinalwb.wanandroid.features.wx.presenter;

import android.util.Log;

import com.chinalwb.wanandroid.features.wx.api.IGzhApi;
import com.chinalwb.wanandroid.features.wx.model.GzhTab;
import com.chinalwb.wanandroid.main.model.ArticlesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WxGzhPresenter implements WxGzhContract.Presenter {

    private WxGzhContract.View mView;
    private IGzhApi mGzhApi;
    private GzhTab mGzhTab;
    private int currentPage;
    private boolean isLoading;

    public WxGzhPresenter(WxGzhContract.View view, IGzhApi gzhApi, GzhTab gzhTab) {
        this.mView = view;
        this.mGzhApi = gzhApi;
        this.mGzhTab = gzhTab;
        this.mView.setPresenter(this);
    }

    @Override
    public void loadArticles(int gzhId, int page) {
        Call<ArticlesResponse> call = mGzhApi.loadGzhArticles(gzhId, page);
        call.enqueue(new Callback<ArticlesResponse>() {
            @Override
            public void onResponse(Call<ArticlesResponse> call, Response<ArticlesResponse> response) {
                 mView.showArticles(response.body().getData().getDatas());
                 toggleLoading(false);
            }

            @Override
            public void onFailure(Call<ArticlesResponse> call, Throwable t) {
                mView.showError(t);
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
        int gzhId = this.mGzhTab.getId();
        Call<ArticlesResponse> call = mGzhApi.loadGzhArticles(gzhId, ++this.currentPage);
        call.enqueue(new Callback<ArticlesResponse>() {
            @Override
            public void onResponse(Call<ArticlesResponse> call, Response<ArticlesResponse>
                    response) {
                mView.appendArticles(response.body().getData().getDatas());
                toggleLoading(false);
            }

            @Override
            public void onFailure(Call<ArticlesResponse> call, Throwable t) {
                mView.showError(t);
                toggleLoading(false);
            }
        });
    }

    @Override
    public void start() {
        if (this.isLoading) {
            return;
        }
        this.toggleLoading(true);
        int gzhId = this.mGzhTab.getId();
        this.currentPage = 0;
        this.loadArticles(gzhId, this.currentPage);
    }

    private void toggleLoading(boolean isLoading) {
        this.isLoading = isLoading;
        if (isLoading) {
            this.mView.showLoading();
        } else {
            this.mView.hideLoading();
        }
    }
}
