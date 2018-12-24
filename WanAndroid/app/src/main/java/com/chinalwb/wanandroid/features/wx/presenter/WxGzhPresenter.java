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
            }

            @Override
            public void onFailure(Call<ArticlesResponse> call, Throwable t) {
                mView.showError(t);
            }
        });
    }

    @Override
    public void start() {
        Log.e("XX", "Start loading..");
        int gzhId = this.mGzhTab.getId();
        this.currentPage = 0;
        this.loadArticles(gzhId, this.currentPage);
    }
}
