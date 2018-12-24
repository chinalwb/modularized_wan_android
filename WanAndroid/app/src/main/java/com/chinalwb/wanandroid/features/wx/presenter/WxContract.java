package com.chinalwb.wanandroid.features.wx.presenter;

import com.chinalwb.wanandroid.base.BasePresenter;
import com.chinalwb.wanandroid.base.BaseView;
import com.chinalwb.wanandroid.features.wx.model.GzhTab;
import com.chinalwb.wanandroid.main.model.Article;

import java.util.List;

public interface WxContract {

    interface Presenter extends BasePresenter {
        void loadGzhTabs();
    }

    interface View extends BaseView<Presenter> {
        void showGzhTabs(List<GzhTab> gzhTabList);
    }

}
