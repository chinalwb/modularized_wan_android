package com.chinalwb.wanandroid_gzh.presenter;

import com.chinalwb.wanandroid_base.base.BasePresenter;
import com.chinalwb.wanandroid_base.base.BaseView;
import com.chinalwb.wanandroid_base.features.article.model.Article;

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
