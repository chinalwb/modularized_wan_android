package com.chinalwb.wanandroid;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.chinalwb.wanandroid.base.RetrofitClient;
import com.chinalwb.wanandroid.main.api.IArticlesApi;
import com.chinalwb.wanandroid.main.presenter.ArticlesPresenter;
import com.chinalwb.wanandroid.main.ui.ArticlesListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    ArticlesPresenter mArticlesPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.setSupportActionBar(toolbar);
        initFragment();
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ArticlesListFragment articlesListFragment = (ArticlesListFragment) fragmentManager.findFragmentById(R.id.fragment_container);
        if (articlesListFragment == null) {
            articlesListFragment = ArticlesListFragment.newInstance();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, articlesListFragment);
            fragmentTransaction.commit();
        }
        IArticlesApi articlesApi = RetrofitClient.getRetrofit().create(IArticlesApi.class);
        mArticlesPresenter = new ArticlesPresenter(articlesApi, articlesListFragment);
    }
}
