package com.chinalwb.wanandroid.features.wx.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chinalwb.wanandroid.R;
import com.chinalwb.wanandroid.features.wx.model.GzhTab;
import com.chinalwb.wanandroid.features.wx.presenter.WxContract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WxFragment extends Fragment implements WxContract.View {

    WxContract.Presenter mPresenter;

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @BindView(R.id.pager)
    ViewPager mViewPager;

    public WxFragment() {
        // Require public empty constructor
        setRetainInstance(true);
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
        if (this.mPresenter != null) {
            this.mPresenter.start();
        }
    }

    @Override
    public void showGzhTabs(List<GzhTab> gzhTabList) {
        WxGzhPagerAdapter pagerAdapter = new WxGzhPagerAdapter(getFragmentManager(), gzhTabList);
        mViewPager.setAdapter(pagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void showError(Throwable error) {
        Toast.makeText(getContext(), "Unknown", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setPresenter(WxContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
