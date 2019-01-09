package com.chinalwb.wanandroid_base;

import com.chinalwb.wanandroid_base.services.contributor.ContributorService;
import com.chinalwb.wanandroid_base.services.navigation.NavigationViewService;

public class ServiceProvider {

    public static NavigationViewService getNavigationViewService() {
        return NavigationViewService.getInstance();
    }

    public static ContributorService getContributorService() {
        return ContributorService.getInstance();
    }

    private ServiceProvider serviceProvider;

    private ServiceProvider() {
        // Private inner constructor
    }

    public static ServiceProvider getInstance() {
        return Inner.serviceProvider;
    }

    private static class Inner {
        private static ServiceProvider serviceProvider = new ServiceProvider();
    }

}
