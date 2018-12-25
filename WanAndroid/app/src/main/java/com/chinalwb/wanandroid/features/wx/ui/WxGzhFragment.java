package com.chinalwb.wanandroid.features.wx.ui;

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

import com.chinalwb.wanandroid.R;
import com.chinalwb.wanandroid.base.Util;
import com.chinalwb.wanandroid.features.wx.model.GzhTab;
import com.chinalwb.wanandroid.features.wx.presenter.WxGzhContract;
import com.chinalwb.wanandroid.main.model.Article;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WxGzhFragment extends Fragment implements WxGzhContract.View {

    private WxGzhContract.Presenter mPresenter;
    private GzhTab mGzhTab;

    @BindView(R.id.articles_recycler_view)
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
        return R.layout.fragment_wx_gzh;
    }

    @Override
    public void onStart() {
        super.onStart();
        initView();
    }

    private void initView() {
        // this.getActivity().setTitle("公众号");

        if (this.mPresenter != null) {
            this.mPresenter.start();
        }
    }

    @Override
    public void showArticles(List<Article> articleList) {
        // RecyclerView
        RecyclerView.Adapter adapter = new GzhArticlesListAdapter(articleList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        this.mRecyclerView.setLayoutManager(layoutManager);
        this.mRecyclerView.setAdapter(adapter);

        Log.e("XX", "End loading .." + articleList.size());
    }

    @Override
    public void setPresenter(WxGzhContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showError(Throwable error) {
        Util.toast(getContext(), "Unknown");
    }
}
