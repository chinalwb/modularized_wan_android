package com.chinalwb.wanandroid_gzh;

import com.chinalwb.wanandroid_base.BaseApplication;
import com.chinalwb.wanandroid_base.ServiceProvider;
import com.chinalwb.wanandroid_base.base.RetrofitClient;
import com.chinalwb.wanandroid_base.services.contributor.ContributorItem;
import com.chinalwb.wanandroid_base.services.contributor.ContributorService;
import com.chinalwb.wanandroid_base.services.navigation.NavigationViewService;
import com.chinalwb.wanandroid_base.services.navigation.NavigationViewItem;
import com.chinalwb.wanandroid_gzh.api.IGzhApi;
import com.chinalwb.wanandroid_gzh.presenter.WxPresenter;
import com.chinalwb.wanandroid_gzh.ui.WxFragment;

import java.util.List;

public class GzhApplication extends BaseApplication {
    @Override
    public void initServiceData() {
        NavigationViewItem navigationViewItem = new NavigationViewItem(
                1,
                R.id.nav_gzh,
                4,
                "公众号"
        );

        // Set WxFragment
        WxFragment wxFragment = WxFragment.newInstance();
        IGzhApi gzhApi = RetrofitClient.getRetrofit().create(IGzhApi.class);
        new WxPresenter(gzhApi, wxFragment);
        navigationViewItem.setFragment(wxFragment);

        NavigationViewService navigationViewService = ServiceProvider.getNavigationViewService();
        List<NavigationViewItem> navigationViewItemList = navigationViewService.getNavigationViewItemList();
        navigationViewItemList.add(navigationViewItem);

        // 初始化 贡献者 数据
        ContributorService contributorService = ServiceProvider.getContributorService();
        List<ContributorItem> contributorItemList  = contributorService.getContributorItemList();
        ContributorItem contributorItem = new ContributorItem(
                "https://avatars0.githubusercontent.com/u/1758864?s=460&v=4",
                "chinalwb",
                "公众号组件",
                "公众号组件为主组件提供了WxFragment作为导航项目入口，内部使用TabLayout + ViewPager实现。" +
                        "ViewPager中的Fragment使用MVP结构实现。",
                "https://github.com/chinalwb"
        );
        contributorItemList.add(contributorItem);

    }
}
