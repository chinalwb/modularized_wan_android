package com.chinalwb.wanandroid.main.presenter;

import com.chinalwb.wanandroid.base.BasePresenter;
import com.chinalwb.wanandroid.base.BaseView;
import com.chinalwb.wanandroid.main.model.Article;

import java.util.List;

public interface ArticlesContract {

    interface Presenter extends BasePresenter {
        void loadArticles(int page);
    }

    interface View extends BaseView<Presenter> {
        void showArticles(List<Article> articleList);
        void showError(String error);
        void showLoading();
        void hideLoading();
    }

}
