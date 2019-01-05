package com.chinalwb.wanandroid_projects;

import android.util.Log;
import android.view.MenuItem;

import com.chinalwb.wanandroid_base.BaseApplication;
import com.chinalwb.wanandroid_base.ServiceProvider;
import com.chinalwb.wanandroid_base.base.RetrofitClient;
import com.chinalwb.wanandroid_base.services.NavigationViewItem;
import com.chinalwb.wanandroid_projects.api.IProjectsApi;
import com.chinalwb.wanandroid_projects.presenter.ProjectsPresenter;
import com.chinalwb.wanandroid_projects.ui.ProjectsFragment;

import java.util.List;

public class ProjectApplication extends BaseApplication {

    @Override
    public void initServiceData() {
        List<NavigationViewItem> navigationItemList = ServiceProvider.getNavigationViewService().getNavigationViewItemList();
        NavigationViewItem navigationViewItem = new NavigationViewItem(
                1,
                R.id.nav_projects,
                2,
                "项目"
        );

        ProjectsFragment projectsFragment = ProjectsFragment.getInstance();
        IProjectsApi projectsApi = RetrofitClient.getRetrofit().create(IProjectsApi.class);
        new ProjectsPresenter(projectsApi, projectsFragment);
        navigationViewItem.setFragment(projectsFragment);

        navigationItemList.add(navigationViewItem);
    }
}
