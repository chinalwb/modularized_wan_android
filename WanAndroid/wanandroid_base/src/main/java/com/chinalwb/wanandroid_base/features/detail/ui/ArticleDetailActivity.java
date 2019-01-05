package com.chinalwb.wanandroid_base.features.detail.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.chinalwb.wanandroid_base.R;
import com.chinalwb.wanandroid_base.R2;
import com.chinalwb.wanandroid_base.features.detail.presenter.ArticleDetailPresenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleDetailActivity extends AppCompatActivity {

    @BindView(R2.id.base_toolbar)
    Toolbar toolbar;

    @BindView(R2.id.base_author)
    TextView authorText;

    @BindView(R2.id.base_date)
    TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_article_detail);

        ButterKnife.bind(this);

        // Set up toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Init title
        String title = getIntent().getStringExtra(ArticleDetailFragment.EXTRA_ARTICLE_TITLE);
        this.setTitle(title);

        // Init fragment
        String url = getIntent().getStringExtra(ArticleDetailFragment.EXTRA_ARTICLE_URL);
        String author = getIntent().getStringExtra(ArticleDetailFragment.EXTRA_ARTICLE_AUTHOR);
        String date = getIntent().getStringExtra(ArticleDetailFragment.EXTRA_ARTICLE_DATE);
        Bundle bundle = new Bundle();
        bundle.putString(ArticleDetailFragment.EXTRA_ARTICLE_URL, url);
        bundle.putString(ArticleDetailFragment.EXTRA_ARTICLE_TITLE, title);
        ArticleDetailFragment articleDetailFragment = ArticleDetailFragment.newInstance();
        articleDetailFragment.setArguments(bundle);
        ArticleDetailPresenter presenter = new ArticleDetailPresenter(articleDetailFragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, articleDetailFragment);
        fragmentTransaction.commit();

        // Init author and date
        authorText.setText(author);
        dateText.setText(date);
    }


    @Override
    public boolean onSupportNavigateUp() {
        super.onSupportNavigateUp();
        this.finish();
        return true;
    }

}
