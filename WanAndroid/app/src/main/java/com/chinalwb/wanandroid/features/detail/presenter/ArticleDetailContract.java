package com.chinalwb.wanandroid.features.detail.presenter;

import android.content.Context;

import com.chinalwb.wanandroid.base.BasePresenter;
import com.chinalwb.wanandroid.base.BaseView;

public interface ArticleDetailContract {

    interface Presenter extends BasePresenter {
        void save(Context context, String url, String fileName);
        void share();
    }

    interface View extends BaseView<Presenter> {
        void showSaveResult(boolean result);
        void showShareResult(boolean result);
    }

}
