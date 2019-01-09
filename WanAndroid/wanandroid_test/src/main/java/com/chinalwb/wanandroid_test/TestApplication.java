package com.chinalwb.wanandroid_test;

import com.chinalwb.wanandroid_base.BaseApplication;
import com.chinalwb.wanandroid_base.ServiceProvider;
import com.chinalwb.wanandroid_base.services.contributor.ContributorItem;
import com.chinalwb.wanandroid_base.services.contributor.ContributorService;
import com.chinalwb.wanandroid_base.services.navigation.NavigationViewItem;
import com.chinalwb.wanandroid_base.services.navigation.NavigationViewService;

import java.util.List;

public class TestApplication extends BaseApplication {
    @Override
    public void initServiceData() {
        // 初始化 导航 数据
        NavigationViewService navigationViewService = ServiceProvider.getNavigationViewService();
        List<NavigationViewItem> navigationViewItemList = navigationViewService.getNavigationViewItemList();

        NavigationViewItem navigationViewItem = new NavigationViewItem(
                1,
                R.id.nav_test,
                99,
                "测试组件"
        );
        TestFragment testFragment = TestFragment.newInstance();
        navigationViewItem.setFragment(testFragment);
        navigationViewItemList.add(navigationViewItem);

        // 初始化 贡献者 数据
        ContributorService contributorService = ServiceProvider.getContributorService();
        List<ContributorItem> contributorItemList = contributorService.getContributorItemList();
        ContributorItem contributorItem = new ContributorItem(
                "http://www.wanandroid.com/resources/image/pc/logo.png",
                "你的名字",
                "测试组件",
                "这是一个测试组件！写完提PR哦！",
                "你的网址"
        );
        contributorItemList.add(contributorItem);
    }
}
