package com.chinalwb.wanandroid_gzh.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinalwb.wanandroid_base.Util;
import com.chinalwb.wanandroid_base.features.article.model.Article;
import com.chinalwb.wanandroid_gzh.R;
import com.chinalwb.wanandroid_gzh.R2;
import com.chinalwb.wanandroid_gzh.model.GzhTab;
import com.chinalwb.wanandroid_gzh.presenter.WxGzhContract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WxGzhFragment extends Fragment implements WxGzhContract.View {

    private WxGzhContract.Presenter mPresenter;
    private GzhTab mGzhTab;
    private GzhArticlesListAdapter mAdapter;

    @BindView(R2.id.gzh_articles_recycler_view)
    RecyclerView mRecyclerView;

    public WxGzhFragment() {
        // Required public empty fragment
    }

    public static WxGzhFragment newInstance() {
        return new WxGzhFragment();
    }

    public void setGzhTab(GzhTab mGzhTab) {
        this.mGzhTab = mGzhTab;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(
                getLayoutId(),
                container,
                false
        );
        ButterKnife.bind(this, view);
        return view;
    }

    private int getLayoutId() {
        return R.layout.gzh_fragment_wx_gzh;
    }

    @Override
    public void onStart() {
        super.onStart();
        initView();
    }

    private void initView() {
        if (this.mPresenter != null) {
            this.mPresenter.start();
        }

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                        .getLayoutManager();
                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition == mAdapter.getItemCount() - 1) {
                    mPresenter.loadNextPageArticles();
                }
            }
        });
    }

    @Override
    public void showArticles(List<Article> articleList) {
        // RecyclerView
        mAdapter = new GzhArticlesListAdapter(articleList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        this.mRecyclerView.setLayoutManager(layoutManager);
        this.mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void appendArticles(List<Article> articleList) {
        mAdapter.appendArticleList(articleList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(WxGzhContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showError(Throwable error) {
        Util.toast(getContext(), "Unknown");
    }

    @Override
    public void showLoading() {
        Log.e("xx", "Show loading..");
    }

    @Override
    public void hideLoading() {
        Log.e("xx", "Hide loading!");
    }
}
