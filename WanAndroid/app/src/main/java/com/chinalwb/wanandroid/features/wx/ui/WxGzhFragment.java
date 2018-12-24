package com.chinalwb.wanandroid.features.wx.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chinalwb.wanandroid.R;
import com.chinalwb.wanandroid.features.wx.model.GzhTab;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WxGzhFragment extends Fragment {

    private GzhTab mGzhTab;

    @BindView(R.id.gzh_name)
    TextView mGzhNameView;

    public WxGzhFragment() {
        // Required public empty fragment
    }

    public static WxGzhFragment newInstance() {
        return new WxGzhFragment();
    }

    public GzhTab getGzhTab() {
        return mGzhTab;
    }

    public void setGzhTab(GzhTab mGzhTab) {
        this.mGzhTab = mGzhTab;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(
                R.layout.fragment_wx_gzh,
                container,
                false
        );
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        mGzhNameView.setText(mGzhTab.getName());
    }
}
