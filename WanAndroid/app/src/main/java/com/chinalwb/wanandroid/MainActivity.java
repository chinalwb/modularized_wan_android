package com.chinalwb.wanandroid;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        initFragment();
    }

    private void initToolbar() {
        this.setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.nav_open_drawer,
                R.string.nav_close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
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
