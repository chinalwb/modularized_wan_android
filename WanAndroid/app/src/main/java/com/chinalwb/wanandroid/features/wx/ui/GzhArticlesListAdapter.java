package com.chinalwb.wanandroid.features.wx.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinalwb.wanandroid.R;
import com.chinalwb.wanandroid.main.model.Article;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GzhArticlesListAdapter extends RecyclerView.Adapter<GzhArticlesListAdapter.ViewHolder> {

    private List<Article> articleList;
    public GzhArticlesListAdapter(List<Article> articleList) {
        this.articleList = articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CardView cardView = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.adapter_item_gzh_article, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(cardView);
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

    class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        @BindView(R.id.favorite_image)
        public ImageView favoriteImageView;

        @BindView(R.id.title_view)
        public TextView title;

        @BindView(R.id.time_view)
        public TextView time;

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
            ButterKnife.bind(this, cardView);
            favoriteImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getLayoutPosition();
                    boolean isCollect = articleList.get(pos).getCollect();
                    articleList.get(pos).setCollect(!isCollect);
                    notifyDataSetChanged();
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
