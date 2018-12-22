package com.chinalwb.wanandroid.main.ui;

import android.content.Context;
import android.gesture.Prediction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chinalwb.wanandroid.R;
import com.chinalwb.wanandroid.base.RetrofitClient;
import com.chinalwb.wanandroid.main.api.IArticlesApi;
import com.chinalwb.wanandroid.main.model.Article;
import com.chinalwb.wanandroid.main.model.ArticlesList;
import com.chinalwb.wanandroid.main.presenter.ArticlesContract;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;


public class ArticlesListFragment extends Fragment implements ArticlesContract.View {

    private ArticlesContract.Presenter mPresenter;

    @BindView(R.id.total_text_view)
    TextView totalTextView;

    public ArticlesListFragment() {
        // Require empty public constructor
        Log.e("XX", ">>> BBB 0");
    }

    public static ArticlesListFragment newInstance() {
        Log.e("XX", ">>> BBB 1");
        return new ArticlesListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("XX", ">>> BBB 2");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("XX", ">>> BBB 3");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles_list, container, false);
        Log.e("XX", ">>> BBB 4");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("XX", ">>> BBB 5 " + mPresenter);
        mPresenter.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("XX", ">>> BBB 6");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("XX", ">>> BBB 7");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("XX", ">>> BBB 8");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("XX", ">>> BBB 9");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("XX", ">>> BBB 12");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("XX", ">>> BBB 10");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("XX", ">>> BBB 11");
    }

    @Override
    public void showArticles(List<Article> articleList) {
        Log.e("XX", "show articles: " + articleList.size());
    }

    @Override
    public void showError(String error) {
        Log.e("XX", "Error");
    }

    @Override
    public void setPresenter(ArticlesContract.Presenter presenter) {
         mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showLoading() {
        Log.e("XX", "Show loading..." + System.currentTimeMillis());
    }

    @Override
    public void hideLoading() {
        Log.e("XX", "Hide loading..." + System.currentTimeMillis());
    }
}
