package com.chinalwb.wanandroid.features.detail;

import android.os.Bundle;

import com.chinalwb.wanandroid.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleDetailActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

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
        Bundle bundle = new Bundle();
        bundle.putString(ArticleDetailFragment.EXTRA_ARTICLE_URL, url);
        ArticleDetailFragment articleDetailFragment = ArticleDetailFragment.newInstance();
        articleDetailFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, articleDetailFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onSupportNavigateUp();
        this.finish();
        return true;
    }
}
