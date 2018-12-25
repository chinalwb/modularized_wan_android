package com.chinalwb.wanandroid.features.wx.ui;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.chinalwb.wanandroid.base.RetrofitClient;
import com.chinalwb.wanandroid.features.wx.api.IGzhApi;
import com.chinalwb.wanandroid.features.wx.model.GzhTab;
import com.chinalwb.wanandroid.features.wx.presenter.WxGzhPresenter;

import java.util.List;

public class WxGzhPagerAdapter extends FragmentPagerAdapter {

    private List<GzhTab> gzhTabList;
    public WxGzhPagerAdapter(FragmentManager fragmentManager, List<GzhTab> gzhTabList) {
        super(fragmentManager);
        this.gzhTabList = gzhTabList;
    }

    public void setGzhTabList(List<GzhTab> gzhTabList) {
        this.gzhTabList = gzhTabList;
        this.notifyDataSetChanged();
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

        IGzhApi gzhApi = RetrofitClient.getRetrofit().create(IGzhApi.class);
        WxGzhPresenter presenter = new WxGzhPresenter(wxGzhFragment, gzhApi, gzhTab);
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
