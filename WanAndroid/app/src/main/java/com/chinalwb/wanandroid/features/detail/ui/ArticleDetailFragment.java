package com.chinalwb.wanandroid.features.detail.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.chinalwb.wanandroid.R;
import com.chinalwb.wanandroid.base.Util;
import com.chinalwb.wanandroid.features.detail.presenter.ArticleDetailContract;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleDetailFragment extends Fragment implements ArticleDetailContract.View {

    ArticleDetailContract.Presenter mPresenter;

    public static final String EXTRA_ARTICLE_URL = "EXTRA_ARTICLE_URL";
    public static final String EXTRA_ARTICLE_TITLE = "EXTRA_ARTICLE_TITLE";
    private String url;
    private String title;

    @BindView(R.id.webview)
    WebView webView;

    public ArticleDetailFragment() {
        // Require public empty constructor
    }

    public static ArticleDetailFragment newInstance() {
        return new ArticleDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.url = getArguments().getString(EXTRA_ARTICLE_URL);
        this.title = getArguments().getString(EXTRA_ARTICLE_TITLE);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(
                R.layout.fragment_article_detail,
                container,
                false
        );
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        this.webView.loadUrl(url);
    }

    private ShareActionProvider shareActionProvider;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_more, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_item_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
//        setShareActionIntent();
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    private void setShareActionIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, title);
        shareActionProvider.setShareIntent(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int menuId = item.getItemId();
        switch (menuId) {
            case R.id.menu_item_save:
                mPresenter.save(getContext(), url, title + ".html");
                break;
            case R.id.menu_item_share:
                 mPresenter.share(getContext(), url, title);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showSaveResult(boolean result) {
        Log.e("xx", "save result == " + result);
    }

    @Override
    public void showShareResult(boolean result) {
        Log.e("xx", "share result == " + result);
    }

    @Override
    public void setPresenter(ArticleDetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showError(Throwable error) {
        Util.toast(getContext(), "Unknown error");
    }
}
