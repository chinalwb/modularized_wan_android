package com.chinalwb.wanandroid_gzh;

import android.app.Service;

import com.chinalwb.wanandroid_base.BaseApplication;
import com.chinalwb.wanandroid_base.ServiceProvider;
import com.chinalwb.wanandroid_base.base.RetrofitClient;
import com.chinalwb.wanandroid_base.services.NavigationViewService;
import com.chinalwb.wanandroid_base.services.NavigationViewItem;
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
    }
}
