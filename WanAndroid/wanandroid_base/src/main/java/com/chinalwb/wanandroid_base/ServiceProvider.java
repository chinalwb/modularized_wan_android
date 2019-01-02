package com.chinalwb.wanandroid_base;

import com.chinalwb.wanandroid_base.services.INavigationViewService;
import com.chinalwb.wanandroid_base.services.NavigationViewService;

public class ServiceProvider {

    public static INavigationViewService getNavigationViewService() {
        return NavigationViewService.getInstance();
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
