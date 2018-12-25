package com.chinalwb.wanandroid.features.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.chinalwb.wanandroid.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleDetailFragment extends Fragment {

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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
}
