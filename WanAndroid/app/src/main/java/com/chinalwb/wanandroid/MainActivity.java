package com.chinalwb.wanandroid;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.chinalwb.wanandroid.base.RetrofitClient;
import com.chinalwb.wanandroid.main.api.IArticlesApi;
import com.chinalwb.wanandroid.main.presenter.ArticlesPresenter;
import com.chinalwb.wanandroid.main.ui.ArticlesListFragment;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fragment_container)
    FrameLayout frameLayout;

    ArticlesPresenter mArticlesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("XX", ">>> AAA onCreate");
        initFragment();
    }

    private void initFragment() {
        Log.e("XX", ">>> AAA 1");
        FragmentManager fragmentManager = getSupportFragmentManager();
        ArticlesListFragment articlesListFragment = (ArticlesListFragment) fragmentManager.findFragmentById(R.id.fragment_container);
        if (articlesListFragment == null) {
            articlesListFragment = ArticlesListFragment.newInstance();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, articlesListFragment);
            fragmentTransaction.commit();
            Log.e("XX", ">>> AAA 2");
        }
        IArticlesApi articlesApi = RetrofitClient.getRetrofit().create(IArticlesApi.class);
        mArticlesPresenter = new ArticlesPresenter(articlesApi, articlesListFragment);
        Log.e("XX", ">>> AAA 3");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("XX", ">>> AAA onRestart");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("XX", ">>> AAA onSaveInstanceState");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("XX", ">>> AAA onDestroy");
    }
}
