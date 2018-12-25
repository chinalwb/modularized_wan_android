package com.chinalwb.wanandroid.main.ui;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chinalwb.wanandroid.R;
import com.chinalwb.wanandroid.base.Constants;
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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.adapter_item_article,
                viewGroup,
                false);
        ViewHolder viewHolder = new ViewHolder(cardView);
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

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;

            ButterKnife.bind(this, cardView);
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
