package com.chinalwb.wanandroid_base.features.detail.presenter;

import android.content.Context;
import android.content.Intent;

import com.chinalwb.wanandroid_base.base.DownloadTask;

public class ArticleDetailPresenter implements ArticleDetailContract.Presenter {

    private ArticleDetailContract.View mView;

    public ArticleDetailPresenter(ArticleDetailContract.View view) {
        this.mView = view;
        this.mView.setPresenter(this);
    }

    @Override
    public void save(Context context, String url, String fileName) {
        DownloadTask downloadTask = new DownloadTask(context, new DownloadTask.DownloadListener() {
            @Override
            public void onSuccess() {
                mView.showSaveResult(true);
            }

            @Override
            public void onFailure() {
                mView.showSaveResult(false);
            }
        });

        downloadTask.execute(url, fileName);
    }

    @Override
    public void share(Context context, String url, String title) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "[WanAndroid]" + title + " : " + url);
        Intent shareIntent = intent.createChooser(intent, "Share");
        context.startActivity(shareIntent);
    }

    @Override
    public void start() {
        // Do nothing in start.
    }
}
