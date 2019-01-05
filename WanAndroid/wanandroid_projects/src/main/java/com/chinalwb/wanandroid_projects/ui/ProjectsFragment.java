package com.chinalwb.wanandroid_projects.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinalwb.wanandroid_base.Util;
import com.chinalwb.wanandroid_base.features.article.model.Article;
import com.chinalwb.wanandroid_projects.R;
import com.chinalwb.wanandroid_projects.R2;
import com.chinalwb.wanandroid_projects.presenter.ProjectsContract;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectsFragment extends Fragment implements ProjectsContract.View {

    ProjectsContract.Presenter mPresenter;

    ProjectsListAdapter mAdapter;

    @BindView(R2.id.projects_recycler_view)
    RecyclerView recyclerView;

    @BindView(R2.id.projects_swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    public ProjectsFragment() {
        // Required empty constructor
    }

    public static ProjectsFragment getInstance() {
        return new ProjectsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.projects_fragment_projects, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupListeners();
        if (mPresenter != null) {
            mPresenter.start();
        }
    }

    private void setupListeners() {
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.start();
            }
        });

        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                        .getLayoutManager();
                int total = recyclerView.getAdapter().getItemCount();
                if (linearLayoutManager.findLastVisibleItemPosition() == (total - 1)) {
                    mPresenter.loadNextPageProjects();
                }
            }
        });
    }

    @Override
    public void appendProjects(List<Article> projectList) {
        mAdapter.appendProjectList(projectList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProjects(List<Article> projectList) {
        mAdapter = new ProjectsListAdapter(projectList);
        recyclerView.setAdapter(mAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void showLoading() {
        this.swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        this.swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setPresenter(ProjectsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showError(Throwable error) {
        Util.toast(getContext(), "Unknown error - projects!");
    }
}
