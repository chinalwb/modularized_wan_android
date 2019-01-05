package com.chinalwb.wanandroid;

import com.chinalwb.wanandroid_base.base.RetrofitClient;
import com.chinalwb.wanandroid.main.api.IArticlesApi;
import com.chinalwb.wanandroid.main.presenter.ArticlesPresenter;
import com.chinalwb.wanandroid.main.ui.ArticlesListFragment;
import com.chinalwb.wanandroid_base.AppConfig;
import com.chinalwb.wanandroid_base.BaseApplication;
import com.chinalwb.wanandroid_base.ServiceProvider;
import com.chinalwb.wanandroid_base.services.INavigationViewService;
import com.chinalwb.wanandroid_base.services.NavigationViewItem;

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
        INavigationViewService navigationViewService = ServiceProvider.getNavigationViewService();
        List<NavigationViewItem> navigationViewItemList = navigationViewService.getNavigationViewItemList();

        NavigationViewItem main = new NavigationViewItem(
                1, R.id.nav_main,1,getResources().getString(R.string.nav_main));
        main.setChecked(true);

        ArticlesListFragment articlesListFragment = ArticlesListFragment.newInstance();
        IArticlesApi articlesApi = RetrofitClient.getRetrofit().create(IArticlesApi.class);
        new ArticlesPresenter(articlesApi, articlesListFragment);
        main.setFragment(articlesListFragment);
        navigationViewItemList.add(main);
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
