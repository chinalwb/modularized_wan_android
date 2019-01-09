package com.chinalwb.wanandroid_projects;

import com.chinalwb.wanandroid_base.BaseApplication;
import com.chinalwb.wanandroid_base.ServiceProvider;
import com.chinalwb.wanandroid_base.base.RetrofitClient;
import com.chinalwb.wanandroid_base.services.contributor.ContributorItem;
import com.chinalwb.wanandroid_base.services.contributor.ContributorService;
import com.chinalwb.wanandroid_base.services.navigation.NavigationViewItem;
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


        // 初始化 贡献者 数据
        ContributorService contributorService = ServiceProvider.getContributorService();
        List<ContributorItem> contributorItemList  = contributorService.getContributorItemList();
        ContributorItem contributorItem = new ContributorItem(
                "https://avatars0.githubusercontent.com/u/1758864?s=460&v=4",
                "chinalwb",
                "项目组件",
                "项目组件为主组件提供了ProjectsFragment作为导航项目入口，Fragment内部使用MVP结构。" +
                        "用Glide来显示RecyclerView#Adapter中的图片。",
                "https://github.com/chinalwb"
        );
        contributorItemList.add(contributorItem);
    }
}
