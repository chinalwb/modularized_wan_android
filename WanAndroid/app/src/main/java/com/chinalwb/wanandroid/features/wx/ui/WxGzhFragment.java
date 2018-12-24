package com.chinalwb.wanandroid.features.wx.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinalwb.wanandroid.R;

public class WxGzhFragment extends Fragment {

    public WxGzhFragment() {
        // Required public empty fragment
    }

    public static WxGzhFragment newInstance() {
        return new WxGzhFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(
                R.layout.fragment_wx_gzh,
                container,
                false
        );
        return view;
    }
}
