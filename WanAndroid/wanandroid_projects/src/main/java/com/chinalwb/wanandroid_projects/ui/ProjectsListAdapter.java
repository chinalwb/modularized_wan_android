package com.chinalwb.wanandroid_projects.ui;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinalwb.wanandroid_base.features.article.model.Article;
import com.chinalwb.wanandroid_projects.R;
import com.chinalwb.wanandroid_projects.R2;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectsListAdapter extends RecyclerView.Adapter<ProjectsListAdapter.ViewHolder> {

    private List<Article> projectList;

    public ProjectsListAdapter(List<Article> projectList) {
        this.projectList = projectList;
    }

    public void appendProjectList(List<Article> addedProjectList) {
        this.projectList.addAll(addedProjectList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CardView cardView = (CardView) layoutInflater.inflate(R.layout.projects_adapter_item,
                parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(this.projectList.get(position));
    }

    @Override
    public int getItemCount() {
        if (this.projectList == null) {
            return 0;
        }
        return this.projectList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        @BindView(R2.id.projects_adapter_favorite_image)
        ImageView favoriteImage;

        @BindView(R2.id.projects_adapter_title)
        TextView titleText;

        @BindView(R2.id.projects_adapter_desc)
        TextView descText;

        @BindView(R2.id.projects_adapter_preview_image)
        ImageView previewImage;

        public ViewHolder(CardView view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(Article project) {
            titleText.setText(project.getTitle());
            descText.setText(project.getDesc());
            if (project.getCollect()) {
                favoriteImage.setImageResource(R.drawable.favorite_yes);
            } else {
                favoriteImage.setImageResource(R.drawable.favorite_no);
            }
        }
    }
}
