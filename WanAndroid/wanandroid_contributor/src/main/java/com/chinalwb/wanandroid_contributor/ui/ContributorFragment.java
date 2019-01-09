package com.chinalwb.wanandroid_contributor.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinalwb.wanandroid_base.Util;
import com.chinalwb.wanandroid_base.services.contributor.ContributorItem;
import com.chinalwb.wanandroid_contributor.R;
import com.chinalwb.wanandroid_contributor.R2;
import com.chinalwb.wanandroid_contributor.presenter.ContributorContract;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ContributorFragment extends Fragment implements ContributorContract.View {

    @BindView(R2.id.contributor_recycler_view)
    RecyclerView recyclerView;

    ContributorContract.Presenter mPresenter;

    ContributorAdapter mAdapter;

    public ContributorFragment() {
        // Required empty constructor
    }

    public static ContributorFragment newInstance() {
        return new ContributorFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.contributor_fragment_contributor, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (null != this.mPresenter) {
            this.mPresenter.start();
        }
    }


    @Override
    public void showContributors(List<ContributorItem> contributorItemList) {
        mAdapter = new ContributorAdapter(contributorItemList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setPresenter(ContributorContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showError(Throwable error) {
        Util.toast(getContext(), "Unknown Error - Contributors.");
    }
}
