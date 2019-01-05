package com.chinalwb.wanandroid_tree;

import com.chinalwb.wanandroid_base.BaseApplication;
import com.chinalwb.wanandroid_base.ServiceProvider;
import com.chinalwb.wanandroid_base.services.INavigationViewService;
import com.chinalwb.wanandroid_base.services.NavigationViewItem;
import com.chinalwb.wanandroid_tree.ui.TreeFragment;

import java.util.List;

public class TreeApplication extends BaseApplication {
    @Override
    public void initServiceData() {

        INavigationViewService navigationViewService = ServiceProvider.getNavigationViewService();
        List<NavigationViewItem> navigationViewItemList = navigationViewService.getNavigationViewItemList();

        NavigationViewItem navigationViewItem = new NavigationViewItem(
                1,
                R.id.nav_tree,
                3,
                "体系"
        );

        navigationViewItem.setFragment(TreeFragment.getInstance());

        navigationViewItemList.add(navigationViewItem);
    }
}
