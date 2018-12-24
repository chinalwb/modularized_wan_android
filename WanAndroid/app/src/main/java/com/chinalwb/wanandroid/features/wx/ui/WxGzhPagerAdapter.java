package com.chinalwb.wanandroid.features.wx.ui;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.chinalwb.wanandroid.features.wx.model.GzhTab;

import java.util.List;

public class WxGzhPagerAdapter extends FragmentPagerAdapter {

    private List<GzhTab> gzhTabList;
    public WxGzhPagerAdapter(FragmentManager fragmentManager, List<GzhTab> gzhTabList) {
        super(fragmentManager);
        this.gzhTabList = gzhTabList;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        GzhTab gzhTab = this.gzhTabList.get(position);
        return gzhTab.getName();
    }

    @Override
    public Fragment getItem(int i) {
        GzhTab gzhTab = this.gzhTabList.get(i);
        WxGzhFragment wxGzhFragment = WxGzhFragment.newInstance();
        wxGzhFragment.setGzhTab(gzhTab);
        return wxGzhFragment;
    }

    @Override
    public int getCount() {
        if (this.gzhTabList == null) {
            return 0;
        }
        return this.gzhTabList.size();
    }
}
