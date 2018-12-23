package com.chinalwb.wanandroid.main.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chinalwb.wanandroid.R;
import com.chinalwb.wanandroid.main.model.Article;
import com.chinalwb.wanandroid.main.presenter.ArticlesPresenter;
import com.google.common.base.Predicates;

import org.w3c.dom.Text;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

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
        CardView cardView = viewHolder.cardView;
        TextView titleView = cardView.findViewById(R.id.title);
        titleView.setText(this.articleList.get(position).getTitle());
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
        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }
}
