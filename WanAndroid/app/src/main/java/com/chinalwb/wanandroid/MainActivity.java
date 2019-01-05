package com.chinalwb.wanandroid;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.chinalwb.wanandroid_base.ServiceProvider;
import com.chinalwb.wanandroid_base.services.INavigationViewService;
import com.chinalwb.wanandroid_base.services.NavigationViewItem;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.chinalwb.wanandroid_base.base.RetrofitClient;
import com.chinalwb.wanandroid.main.api.IArticlesApi;
import com.chinalwb.wanandroid.main.presenter.ArticlesPresenter;
import com.chinalwb.wanandroid.main.ui.ArticlesListFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String CURRENT_VIEW_ID = "CURRENT_VIEW_ID";

    private List<NavigationViewItem> navigationViewItemList;

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
        initNavigationView();
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
        this.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        this.navigationView.setNavigationItemSelectedListener(this);
    }

    private void initNavigationView() {
        Menu menu = this.navigationView.getMenu();

        INavigationViewService navigationViewService = ServiceProvider.getNavigationViewService();
        this.navigationViewItemList = navigationViewService.getNavigationViewItemList();

        for (NavigationViewItem navigationViewItem : this.navigationViewItemList) {
            int groupId = navigationViewItem.getGroupId();
            int itemId = navigationViewItem.getItemId();
            int order = navigationViewItem.getOrder();
            CharSequence title = navigationViewItem.getTitle();
            MenuItem menuItem = menu.add(groupId, itemId, order, title);

            // Icon Resource
            int iconRes = navigationViewItem.getIconRes();
            if (iconRes != 0) {
                menuItem.setIcon(iconRes);
            }
            menuItem.setCheckable(true);
            menuItem.setChecked(navigationViewItem.isChecked());

            if (navigationViewItem.isChecked()) {
                this.setTitle(title);
            }
        }
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

        Log.e("XX", "Menu Id == " + menuId);

        boolean handled = false;
        if (menuId == this.currentViewId) {
            handled = true;
        } else {
            this.currentViewId = menuId;
        }

        if (!handled) {
            showFragment();
            menuItem.setChecked(true);
        }

        CharSequence title = menuItem.getTitle();
        this.setTitle(title);
        return true;
    }

    private void showFragment() {
        for (NavigationViewItem navigationViewItem : this.navigationViewItemList) {
            int navigationViewItemId = navigationViewItem.getItemId();
            if (navigationViewItemId == this.currentViewId) {
                navigationViewItem.showFragment(getSupportFragmentManager(), R.id.fragment_container);
                return;
            }
        }
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
        ArticlesPresenter articlesPresenter = new ArticlesPresenter(articlesApi,
                articlesListFragment);
        Log.e("XX", "show articles fragment == " + articlesListFragment);
    }

    /**
     * Needs to be dynamic loaded
     *
     * @TODO
     */
    private void showWx() {

    }
}
