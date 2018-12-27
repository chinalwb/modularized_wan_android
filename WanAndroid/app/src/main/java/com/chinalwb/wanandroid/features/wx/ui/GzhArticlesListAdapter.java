package com.chinalwb.wanandroid.features.wx.ui;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinalwb.wanandroid.R;
import com.chinalwb.wanandroid.features.detail.ui.ArticleDetailActivity;
import com.chinalwb.wanandroid.features.detail.ui.ArticleDetailFragment;
import com.chinalwb.wanandroid.main.model.Article;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GzhArticlesListAdapter extends RecyclerView.Adapter<GzhArticlesListAdapter.ViewHolder> {

    private List<Article> articleList;
    public GzhArticlesListAdapter(List<Article> articleList) {
        this.articleList = articleList;
    }

    public void appendArticleList(List<Article> articleList) {
        this.articleList.addAll(articleList);
    }

    private OnItemClickListener itemClickListener = new OnItemClickListener() {
        @Override
        public void onFavoriteClicked(View v, int pos) {
            Article article = articleList.get(pos);
            boolean isCollect = article.getCollect();
            article.setCollect(!isCollect);
            notifyDataSetChanged();
        }

        @Override
        public void onItemClicked(View v, int pos) {
            Intent intent = new Intent(v.getContext(), ArticleDetailActivity.class);

            Article article = articleList.get(pos);
            String url = article.getLink();
            String title = article.getTitle();
            intent.putExtra(ArticleDetailFragment.EXTRA_ARTICLE_URL, url);
            intent.putExtra(ArticleDetailFragment.EXTRA_ARTICLE_TITLE, title);
            v.getContext().startActivity(intent);
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CardView cardView = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.adapter_item_gzh_article, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(cardView, itemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Article article = this.articleList.get(i);
        viewHolder.bindTo(article);
    }

    @Override
    public int getItemCount() {
        return this.articleList.size();
    }

    interface OnItemClickListener {
        void onFavoriteClicked(View v, int pos);
        void onItemClicked(View v, int pos);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private OnItemClickListener itemClickListener;

        @BindView(R.id.favorite_image)
        public ImageView favoriteImageView;

        @BindView(R.id.title_view)
        public TextView title;

        @BindView(R.id.time_view)
        public TextView time;

        public ViewHolder(CardView cardView, OnItemClickListener itemClickListener) {
            super(cardView);
            this.cardView = cardView;
            this.itemClickListener = itemClickListener;

            ButterKnife.bind(this, cardView);
            favoriteImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getLayoutPosition();
                    itemClickListener.onFavoriteClicked(v, pos);
                }
            });

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getLayoutPosition();
                    itemClickListener.onItemClicked(v, pos);
                }
            });
        }

        public void bindTo(Article article) {
            this.title.setText(article.getTitle());
            this.time.setText(article.getNiceDate());
            if (article.getCollect()) {
                favoriteImageView.setImageResource(R.drawable.favorite_yes);
            } else {
                favoriteImageView.setImageResource(R.drawable.favorite_no);
            }
        }
    }
}
