package com.chinalwb.wanandroid;

import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;

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
                1, 1,1,getResources().getString(R.string.nav_main));
        main.setChecked(true);
        navigationViewItemList.add(main);
        main.setClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.e("XX", "Show Fragment!!");
                return false;
            }
        });
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
