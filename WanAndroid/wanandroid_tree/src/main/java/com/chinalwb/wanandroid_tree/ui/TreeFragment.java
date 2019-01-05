package com.chinalwb.wanandroid_tree.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinalwb.wanandroid_tree.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TreeFragment extends Fragment {

    public TreeFragment() {
        // Required public empty constructor
    }

    public static TreeFragment getInstance() {
        return new TreeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tree, container, false);
        return view;
    }
}
