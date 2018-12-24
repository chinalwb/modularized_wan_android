package com.chinalwb.wanandroid.features.wx.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinalwb.wanandroid.R;
import com.chinalwb.wanandroid.features.wx.presenter.WxContract;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WxFragment extends Fragment implements WxContract.View {

    WxContract.Presenter mPresenter;

    @BindView(R.id.pager)
    ViewPager viewPager;

    public WxFragment() {
        // Require public empty constructor
    }

    public static WxFragment newInstance() {
        return new WxFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(
          R.layout.fragment_wx,
          container,
          false
        );

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        WxGzhPagerAdapter pagerAdapter = new WxGzhPagerAdapter(getFragmentManager());
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void showGzhTabs() {
        // Init view pager here
        // @TODO
    }

    @Override
    public void setPresenter(WxContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
