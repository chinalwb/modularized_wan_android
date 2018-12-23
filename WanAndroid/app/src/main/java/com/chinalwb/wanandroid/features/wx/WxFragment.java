package com.chinalwb.wanandroid.features.wx;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinalwb.wanandroid.R;

public class WxFragment extends Fragment {

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
        return view;
    }
}
