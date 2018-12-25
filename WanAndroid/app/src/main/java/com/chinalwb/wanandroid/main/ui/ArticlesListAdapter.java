package com.chinalwb.wanandroid.main.ui;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chinalwb.wanandroid.R;
import com.chinalwb.wanandroid.base.Constants;
import com.chinalwb.wanandroid.features.detail.ArticleDetailActivity;
import com.chinalwb.wanandroid.features.detail.ArticleDetailFragment;
import com.chinalwb.wanandroid.main.model.Article;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

public class ArticlesListAdapter extends RecyclerView.Adapter<ArticlesListAdapter.ViewHolder> {


    private List<Article> articleList;

    public ArticlesListAdapter(@NonNull List<Article> articleList) {
        this.articleList = articleList;
    }

    public void appendArticles(List<Article> articleList) {
        this.articleList.addAll(articleList);
    }

    private OnItemClickListener listener = new OnItemClickListener() {
        @Override
        public void onItemClicked(View view, int pos) {
            Article article = articleList.get(pos);
            String title = article.getTitle();
            String url = article.getLink();
            Context context = view.getContext();
            Intent intent = new Intent(context, ArticleDetailActivity.class);
            intent.putExtra(ArticleDetailFragment.EXTRA_ARTICLE_TITLE, title);
            intent.putExtra(ArticleDetailFragment.EXTRA_ARTICLE_URL, url);
            context.startActivity(intent);
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.adapter_item_article,
                viewGroup,
                false);
        ViewHolder viewHolder = new ViewHolder(cardView, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.bindTo(articleList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        checkNotNull(this.articleList);
        return this.articleList.size();
    }

    interface OnItemClickListener {
        void onItemClicked(View view, int pos);
    }

    /**
     *
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        @BindView(R.id.type_view)
        public View typeView;

        @BindView(R.id.title)
        public TextView titleView;

        @BindView(R.id.author)
        public TextView authorView;

        @BindView(R.id.category)
        public TextView categoryView;

        @BindView(R.id.subCategory)
        public TextView subCategoryView;

        @BindView(R.id.timeView)
        public TextView timeView;

        public OnItemClickListener listener;

        public ViewHolder(CardView cardView, OnItemClickListener listener) {
            super(cardView);
            this.cardView = cardView;
            this.listener = listener;

            ButterKnife.bind(this, cardView);
            this.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getLayoutPosition();
                    listener.onItemClicked(v, pos);
                }
            });
        }

        public void bindTo(Article article) {
            int typeColor = article.isGZH() ? Constants.COLOR_WX : Constants.COLOR_WAN;
            this.typeView.setBackgroundColor(typeColor);
            this.titleView.setText(article.getTitle());
            this.authorView.setText(article.getAuthor());
            this.categoryView.setText(article.getSuperChapterName());
            this.subCategoryView.setText("/" + article.getChapterName());
            this.timeView.setText(article.getNiceDate());
        }
    }
}
