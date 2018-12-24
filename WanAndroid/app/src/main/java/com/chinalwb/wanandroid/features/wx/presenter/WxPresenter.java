package com.chinalwb.wanandroid.features.wx.presenter;

import com.chinalwb.wanandroid.features.wx.api.IGzhApi;

public class WxPresenter implements WxContract.Presenter {

    private IGzhApi gzhApi;
    private WxContract.View wxView;

    public WxPresenter(IGzhApi gzhApi, WxContract.View view) {
        this.gzhApi = gzhApi;
        this.wxView = view;
    }

    @Override
    public void loadGzhTabs() {
        // @TODO this.gzhApi.loadGzhTabs();
    }

    @Override
    public void start() {
        loadGzhTabs();
    }
}
