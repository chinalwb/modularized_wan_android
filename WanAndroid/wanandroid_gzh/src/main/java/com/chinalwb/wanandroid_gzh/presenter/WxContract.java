package com.chinalwb.wanandroid_gzh.presenter;

import com.chinalwb.wanandroid_base.base.BasePresenter;
import com.chinalwb.wanandroid_base.base.BaseView;
import com.chinalwb.wanandroid_gzh.model.GzhTab;

import java.util.List;

public interface WxContract {

    interface Presenter extends BasePresenter {
        void loadGzhTabs();
    }

    interface View extends BaseView<Presenter> {
        void showGzhTabs(List<GzhTab> gzhTabList);
    }

}
