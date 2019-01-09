package com.chinalwb.wanandroid_contributor.presenter;

import com.chinalwb.wanandroid_base.ServiceProvider;
import com.chinalwb.wanandroid_base.services.contributor.ContributorItem;
import com.chinalwb.wanandroid_base.services.contributor.ContributorService;

import java.util.List;

public class ContributorPresenter implements ContributorContract.Presenter {

    private ContributorContract.View view;
    private ContributorService contributorService;

    public ContributorPresenter(ContributorContract.View view, ContributorService contributorService) {
        this.view = view;
        this.contributorService = contributorService;
        this.view.setPresenter(this);
    }

    @Override
    public void loadContributors() {
        List<ContributorItem> contributorItemList = contributorService.getContributorItemList();
        view.showContributors(contributorItemList);
    }

    @Override
    public void start() {
        this.loadContributors();
    }
}
