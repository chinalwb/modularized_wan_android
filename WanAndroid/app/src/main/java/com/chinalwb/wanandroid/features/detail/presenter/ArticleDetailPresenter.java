package com.chinalwb.wanandroid.features.detail.presenter;

import android.content.Context;
import android.content.Intent;

import com.chinalwb.wanandroid.base.DownloadService;

public class ArticleDetailPresenter implements ArticleDetailContract.Presenter {

    private ArticleDetailContract.View mView;

    public ArticleDetailPresenter(ArticleDetailContract.View view) {
        this.mView = view;
        this.mView.setPresenter(this);
    }

    @Override
    public void save(Context context, String url, String fileName) {
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(DownloadService.EXTRA_URL, url);
        intent.putExtra(DownloadService.EXTRA_FILENAME, fileName);
        context.startService(intent);
    }

    @Override
    public void share() {

    }

    @Override
    public void start() {
        // Do nothing in start.
    }
}
