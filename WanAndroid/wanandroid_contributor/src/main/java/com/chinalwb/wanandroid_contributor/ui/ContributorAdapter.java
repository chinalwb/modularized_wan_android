package com.chinalwb.wanandroid_contributor.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chinalwb.wanandroid_base.services.contributor.ContributorItem;
import com.chinalwb.wanandroid_contributor.R;
import com.chinalwb.wanandroid_contributor.R2;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ContributorAdapter extends RecyclerView.Adapter<ContributorAdapter.ViewHolder> {

    private List<ContributorItem> contributorItemList;

    public ContributorAdapter(List<ContributorItem> contributorItems) {
        this.contributorItemList = contributorItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CardView view = (CardView) layoutInflater.inflate(R.layout.contributor_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(this.contributorItemList.get(position));
    }

    @Override
    public int getItemCount() {
        if (this.contributorItemList == null) {
            return 0;
        }
        return this.contributorItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        @BindView(R2.id.contributor_adapter_avatar_image)
        ImageView avatarImage;

        @BindView(R2.id.contributor_adapter_author_text)
        TextView authorText;

        @BindView(R2.id.contributor_adapter_component_name)
        TextView componentNameText;

        @BindView(R2.id.contributor_adapter_component_desc)
        TextView componentDescText;

        @BindView(R2.id.contributor_adapter_github)
        TextView githubText;

        public ViewHolder(CardView view) {
            super(view);
            this.cardView = view;
            ButterKnife.bind(this, view);
        }

        void bind(ContributorItem contributorItem) {
            Glide.with(cardView).load(contributorItem.getAvatarUrl()).into(avatarImage);
            authorText.setText(contributorItem.getAuthor());
            componentNameText.setText(contributorItem.getComponentName());
            componentDescText.setText(contributorItem.getComponentDesc());
            githubText.setText(contributorItem.getGithub());
        }
    }
}
