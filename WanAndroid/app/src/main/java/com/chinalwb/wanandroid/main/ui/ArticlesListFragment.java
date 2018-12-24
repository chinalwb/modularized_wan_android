package com.chinalwb.wanandroid.main.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chinalwb.wanandroid.R;
import com.chinalwb.wanandroid.main.model.Article;
import com.chinalwb.wanandroid.main.presenter.ArticlesContract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;


public class ArticlesListFragment extends Fragment implements ArticlesContract.View {

    private ArticlesContract.Presenter mPresenter;
    private ArticlesListAdapter mAdapter;

    @BindView(R.id.articles_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    public ArticlesListFragment() {
        // Require empty public constructor
    }

    public static ArticlesListFragment newInstance() {
        return new ArticlesListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    private int getLayoutId() {
        return R.layout.fragment_articles_list;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        addListeners();
        if (mPresenter != null) {
            mPresenter.start();
        }
    }

    private void initViews() {
//        this.recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL));
    }

    private void addListeners() {
        // SwipeRefreshLayout
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refreshArticles();
            }
        });

        // RecyclerView
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager.findLastVisibleItemPosition() == recyclerView.getAdapter().getItemCount() - 1) {
                    mPresenter.loadNextPageArticles();
                }
            }
        });
    }

    @Override
    public void showArticles(List<Article> articleList) {
        this.mAdapter = new ArticlesListAdapter(articleList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        this.recyclerView.setLayoutManager(gridLayoutManager);
        this.recyclerView.setAdapter(this.mAdapter);
    }

    @Override
    public void appendArticles(List<Article> articleList) {
        this.mAdapter.appendArticles(articleList);
        this.mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(Throwable error) {
        Toast.makeText(getContext(), "Unknown", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setPresenter(ArticlesContract.Presenter presenter) {
        Log.e("XX", "Set presenter fragment = " + this);
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showLoading() {
        Log.e("XX", "Show loading..." + System.currentTimeMillis());
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        Log.e("XX", "Hide loading..." + System.currentTimeMillis());
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("XX", "Fragment destroy = " + this);
    }
}
