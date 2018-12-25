package com.chinalwb.wanandroid.features.wx.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

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

    WxGzhPagerAdapter mPagerAdapter;

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @BindView(R.id.pager)
    ViewPager mViewPager;

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
        if (this.mPresenter != null) {
            this.mPresenter.start();
        }
    }

    @Override
    public void showGzhTabs(List<GzhTab> gzhTabList) {
        mPagerAdapter = new WxGzhPagerAdapter(getChildFragmentManager(), gzhTabList);
        mViewPager.setAdapter(mPagerAdapter);
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
