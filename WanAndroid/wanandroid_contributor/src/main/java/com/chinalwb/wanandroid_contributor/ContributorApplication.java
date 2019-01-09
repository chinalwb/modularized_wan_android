package com.chinalwb.wanandroid_contributor;

import com.chinalwb.wanandroid_base.BaseApplication;
import com.chinalwb.wanandroid_base.ServiceProvider;
import com.chinalwb.wanandroid_base.services.contributor.ContributorItem;
import com.chinalwb.wanandroid_base.services.contributor.ContributorService;
import com.chinalwb.wanandroid_base.services.navigation.NavigationViewItem;
import com.chinalwb.wanandroid_base.services.navigation.NavigationViewService;
import com.chinalwb.wanandroid_contributor.presenter.ContributorPresenter;
import com.chinalwb.wanandroid_contributor.ui.ContributorFragment;

import java.util.List;

public class ContributorApplication extends BaseApplication {
    @Override
    public void initServiceData() {
        // 初始化 导航 数据
        NavigationViewService navigationViewService = ServiceProvider.getNavigationViewService();
        List<NavigationViewItem> navigationViewItemList = navigationViewService.getNavigationViewItemList();

        NavigationViewItem navigationViewItem = new NavigationViewItem(
                1,
                R.id.nav_contributor,
                100,
                "贡献者"
        );
        ContributorFragment contributorFragment = ContributorFragment.newInstance();
        new ContributorPresenter(contributorFragment, ServiceProvider.getContributorService());
        navigationViewItem.setFragment(contributorFragment);
        navigationViewItemList.add(navigationViewItem);

        // 初始化 贡献者 数据
        ContributorService contributorService = ServiceProvider.getContributorService();
        List<ContributorItem> contributorItemList = contributorService.getContributorItemList();
        ContributorItem contributorItem = new ContributorItem(
                "https://avatars0.githubusercontent.com/u/1758864?s=460&v=4",
                "chinalwb",
                "贡献者列表组件",
                "显示所有组件的贡献者。快来写一个自己的组件吧！写完提PR哦！",
                "https://github.com/chinalwb"
        );
        contributorItemList.add(contributorItem);
    }
}
