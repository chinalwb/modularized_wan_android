package com.chinalwb.wanandroid.features.wx.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class WxGzhPagerAdapter extends FragmentPagerAdapter {

    public WxGzhPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int i) {
        return WxGzhFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
