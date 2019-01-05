package com.chinalwb.wanandroid_base.features.detail.presenter;

import android.content.Context;

import com.chinalwb.wanandroid_base.base.BasePresenter;
import com.chinalwb.wanandroid_base.base.BaseView;

public interface ArticleDetailContract {

    interface Presenter extends BasePresenter {
        void save(Context context, String url, String fileName);
        void share(Context context, String url, String title);
    }

    interface View extends BaseView<Presenter> {
        void showSaveResult(boolean result);
        void showShareResult(boolean result);
    }

}
