package com.chinalwb.wanandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.chinalwb.wanandroid.base.RetrofitClient;
import com.chinalwb.wanandroid.features.wx.api.IGzhApi;
import com.chinalwb.wanandroid.features.wx.presenter.WxPresenter;
import com.chinalwb.wanandroid.features.wx.ui.WxFragment;
import com.chinalwb.wanandroid.main.api.IArticlesApi;
import com.chinalwb.wanandroid.main.presenter.ArticlesPresenter;
import com.chinalwb.wanandroid.main.ui.ArticlesListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String CURRENT_VIEW_ID = "CURRENT_VIEW_ID";

    ArticlesPresenter mArticlesPresenter;

    private int currentViewId = R.id.nav_main;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("XX", "Create activity == " + this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(CURRENT_VIEW_ID)) {
                this.currentViewId = savedInstanceState.getInt(CURRENT_VIEW_ID);
            }
        }
        initToolbar();
        showFragment();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("XX", "Restart activity == " + this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("XX", "Destroy activity == " + this);
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

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_VIEW_ID, this.currentViewId);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawer(GravityCompat.START);
        int menuId = menuItem.getItemId();
        boolean handled = false;
        if (menuId == this.currentViewId) {
            handled = true;
        } else {
            this.currentViewId = menuId;
        }

        if (!handled) {
            showFragment();
            menuItem.setCheckable(true);
        }
        return true;
    }

    private void showFragment() {
        switch (this.currentViewId) {
            case R.id.nav_main:
                showArticles();
                break;
            case R.id.nav_wx:
                showWx();
                break;
        }
    }

    private void showArticles() {
        ArticlesListFragment articlesListFragment = ArticlesListFragment.newInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, articlesListFragment);
        fragmentTransaction.commit();

        IArticlesApi articlesApi = RetrofitClient.getRetrofit().create(IArticlesApi.class);
        mArticlesPresenter = new ArticlesPresenter(articlesApi, articlesListFragment);
        Log.e("XX", "show articles fragment == " + articlesListFragment);
    }

    /**
     * Needs to be dynamic loaded
     * @TODO
     */
    private void showWx() {
        WxFragment wxFragment = WxFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, wxFragment);
        fragmentTransaction.commit();

        IGzhApi gzhApi = RetrofitClient.getRetrofit().create(IGzhApi.class);
        WxPresenter wxPresenter = new WxPresenter(gzhApi, wxFragment);
    }
}
