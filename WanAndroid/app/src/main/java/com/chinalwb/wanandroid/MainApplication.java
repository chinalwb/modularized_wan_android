package com.chinalwb.wanandroid;

import com.chinalwb.wanandroid_base.base.RetrofitClient;
import com.chinalwb.wanandroid.main.api.IArticlesApi;
import com.chinalwb.wanandroid.main.presenter.ArticlesPresenter;
import com.chinalwb.wanandroid.main.ui.ArticlesListFragment;
import com.chinalwb.wanandroid_base.AppConfig;
import com.chinalwb.wanandroid_base.BaseApplication;
import com.chinalwb.wanandroid_base.ServiceProvider;
import com.chinalwb.wanandroid_base.services.contributor.ContributorItem;
import com.chinalwb.wanandroid_base.services.contributor.ContributorService;
import com.chinalwb.wanandroid_base.services.navigation.NavigationViewService;
import com.chinalwb.wanandroid_base.services.navigation.NavigationViewItem;

import java.util.List;

public class MainApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initServiceData();
        initModuleServiceData();
    }

    @Override
    public void initServiceData() {

        // 初始化 导航 数据
        NavigationViewService navigationViewService = ServiceProvider.getNavigationViewService();
        List<NavigationViewItem> navigationViewItemList = navigationViewService.getNavigationViewItemList();

        NavigationViewItem main = new NavigationViewItem(
                1, R.id.nav_main,1,getResources().getString(R.string.nav_main));
        main.setChecked(true);

        ArticlesListFragment articlesListFragment = ArticlesListFragment.newInstance();
        IArticlesApi articlesApi = RetrofitClient.getRetrofit().create(IArticlesApi.class);
        new ArticlesPresenter(articlesApi, articlesListFragment);
        main.setFragment(articlesListFragment);
        navigationViewItemList.add(main);


        // 初始化 贡献者 数据
        ContributorService contributorService = ServiceProvider.getContributorService();
        List<ContributorItem> contributorItemList  = contributorService.getContributorItemList();
        ContributorItem contributorItem = new ContributorItem(
            "https://avatars0.githubusercontent.com/u/1758864?s=460&v=4",
                "chinalwb",
                "App 入口",
                "App组件化的调度者。" +
                        "\r\n这是我学习用组件化（本例中用模块化更为恰当）思想开发一个App的实现。其中，我发现组件化好像没有在其他地方看到的那么复杂，希望路过的大神不吝赐教。" +
                        "\r\n如果你也是刚开始接触组件化，何不写一个组件一起学习呢？" +
                        "\r\n该组件中目前包含了首页内容的显示代码，即将分离开来。",
                "https://github.com/chinalwb"
        );
        contributorItemList.add(contributorItem);
    }

    private void initModuleServiceData() {
        for (String module : AppConfig.MODULES) {
            try {
                Class clazz = Class.forName(module);
                BaseApplication baseApplication = (BaseApplication) clazz.newInstance();
                baseApplication.initServiceData();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
