package com.chinalwb.wanandroid_projects;

import android.util.Log;
import android.view.MenuItem;

import com.chinalwb.wanandroid_base.BaseApplication;
import com.chinalwb.wanandroid_base.ServiceProvider;
import com.chinalwb.wanandroid_base.services.NavigationViewItem;

import java.util.List;

public class ProjectApplication extends BaseApplication {

    @Override
    public void initServiceData() {
        List<NavigationViewItem> navigationItemList = ServiceProvider.getNavigationViewService().getNavigationViewItemList();
        NavigationViewItem navigationViewItem = new NavigationViewItem(
                1,
                2,
                2,
                "项目"
        );
        navigationViewItem.setClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.e("XX", "Show Project Fragment!!");
                return false;
            }
        });

        navigationItemList.add(navigationViewItem);
    }
}
