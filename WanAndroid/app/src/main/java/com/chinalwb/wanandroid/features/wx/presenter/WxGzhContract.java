package com.chinalwb.wanandroid.features.wx.presenter;

import com.chinalwb.wanandroid.base.BasePresenter;
import com.chinalwb.wanandroid.base.BaseView;
import com.chinalwb.wanandroid.main.model.Article;

import java.util.List;

public interface WxGzhContract {

    interface Presenter extends BasePresenter {
        void loadArticles(int gzhId, int pageId);
    }

    interface View extends BaseView<Presenter> {
        void showArticles(List<Article> articleList);
    }

}
