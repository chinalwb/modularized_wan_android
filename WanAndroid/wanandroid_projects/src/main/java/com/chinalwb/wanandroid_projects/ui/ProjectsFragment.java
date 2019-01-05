package com.chinalwb.wanandroid_projects.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinalwb.wanandroid_projects.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProjectsFragment extends Fragment {

    public ProjectsFragment() {
        // Required empty constructor
    }

    public static ProjectsFragment getInstance() {
        return new ProjectsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_projects, container, false);
        return view;
    }
}
