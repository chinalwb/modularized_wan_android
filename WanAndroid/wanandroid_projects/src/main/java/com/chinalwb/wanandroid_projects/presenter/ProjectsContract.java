package com.chinalwb.wanandroid_projects.presenter;

import com.chinalwb.wanandroid_base.base.BasePresenter;
import com.chinalwb.wanandroid_base.base.BaseView;
import com.chinalwb.wanandroid_base.features.article.model.Article;

import java.util.List;

public interface ProjectsContract {

    interface Presenter extends BasePresenter {
        void loadProjects(int page);
        void loadNextPageProjects();
        void refreshProjects();
    }

    interface View extends BaseView<Presenter> {
        void appendProjects(List<Article> projectList);
        void showProjects(List<Article> projectList);
        void showLoading();
        void hideLoading();
    }
}
