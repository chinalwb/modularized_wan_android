package com.chinalwb.wanandroid_contributor.presenter;

import com.chinalwb.wanandroid_base.base.BasePresenter;
import com.chinalwb.wanandroid_base.base.BaseView;
import com.chinalwb.wanandroid_base.services.contributor.ContributorItem;

import java.util.List;

public interface ContributorContract {
    interface View extends BaseView<Presenter> {
        void showContributors(List<ContributorItem> contributorItemList);
    }

    interface Presenter extends BasePresenter  {
        void loadContributors();
    }
}
