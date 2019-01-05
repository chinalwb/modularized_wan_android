package com.chinalwb.wanandroid.main.presenter;

import com.chinalwb.wanandroid_base.base.BasePresenter;
import com.chinalwb.wanandroid_base.base.BaseView;
import com.chinalwb.wanandroid_base.features.article.model.Article;

import java.util.List;

public interface ArticlesContract {

    interface Presenter extends BasePresenter {
        void loadArticles(int page);
        void loadNextPageArticles();
        void refreshArticles();
    }

    interface View extends BaseView<Presenter> {
        void appendArticles(List<Article> articleList);
        void showArticles(List<Article> articleList);
        void showLoading();
        void hideLoading();
    }

}
