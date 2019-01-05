package com.chinalwb.wanandroid_gzh.presenter;

import com.chinalwb.wanandroid_gzh.api.IGzhApi;
import com.chinalwb.wanandroid_gzh.model.GzhListResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WxPresenter implements WxContract.Presenter {

    private IGzhApi gzhApi;
    private WxContract.View wxView;

    public WxPresenter(IGzhApi gzhApi, WxContract.View view) {
        this.gzhApi = gzhApi;
        this.wxView = view;
        this.wxView.setPresenter(this);
    }

    @Override
    public void loadGzhTabs() {
        Call<GzhListResponse> call = this.gzhApi.loadGzhTabs();
        call.enqueue(new Callback<GzhListResponse>() {
            @Override
            public void onResponse(Call<GzhListResponse> call, Response<GzhListResponse> response) {
                wxView.showGzhTabs(response.body().getData());
            }

            @Override
            public void onFailure(Call<GzhListResponse> call, Throwable t) {
                wxView.showError(t);
            }
        });
    }

    @Override
    public void start() {
        loadGzhTabs();
    }
}
