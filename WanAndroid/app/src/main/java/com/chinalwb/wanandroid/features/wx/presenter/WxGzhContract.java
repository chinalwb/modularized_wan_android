package com.chinalwb.wanandroid.features.wx.presenter;

import com.chinalwb.wanandroid.base.BasePresenter;
import com.chinalwb.wanandroid.base.BaseView;
import com.chinalwb.wanandroid.main.model.Article;

import java.util.List;

public interface WxGzhContract {

    interface Presenter extends BasePresenter {
        void loadArticles(int gzhId, int page);
        void loadNextPageArticles();
    }

    interface View extends BaseView<Presenter> {
        void showArticles(List<Article> articleList);
        void appendArticles(List<Article> articleList);
        void showLoading();
        void hideLoading();
    }

}
