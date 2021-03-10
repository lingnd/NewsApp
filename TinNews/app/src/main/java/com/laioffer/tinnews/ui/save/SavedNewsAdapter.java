package com.laioffer.tinnews.ui.save;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.tinnews.databinding.SavedNewsItemBinding;
import com.laioffer.tinnews.model.Article;
import com.laioffer.tinnews.R;


import java.util.ArrayList;
import java.util.List;

public class SavedNewsAdapter extends RecyclerView.Adapter<SavedNewsAdapter.SavedNewsViewHolder> {

    interface ItemCallback {
        //for opening a new fragment for article details.
        void onOpenDetails(Article article);
        //to remove articles in the saved database.
        void onRemoveFavorite(Article article);
    }
    //The actual code ordering is rearranged when putting together with other existing code.
    // We usually put interface definition at the top, then private variables, lastly functions.

    private ItemCallback itemCallback;

    // 1. Supporting data:
    private List<Article> articles = new ArrayList<>();

    public void setArticles(List<Article> newsList) {
        articles.clear();
        articles.addAll(newsList);
        notifyDataSetChanged();
    }

    //We call viewModel.deleteSavedArticle when onRemoveFavorite happens.
    public void setItemCallback(ItemCallback itemCallback) {
        this.itemCallback = itemCallback;
    }


    // 2. Adapter overrides:
    @NonNull
    @Override
    public SavedNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_news_item, parent, false);
        return new SavedNewsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull SavedNewsViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.authorTextView.setText(article.author);
        holder.descriptionTextView.setText(article.description);
        //Use the itemCallback to inform the implementer the onRemoveFavorite event
        // when the favoriteIcon is clicked, also inform the opening for details event.
        holder.favoriteIcon.setOnClickListener(v -> itemCallback.onRemoveFavorite(article));
        holder.itemView.setOnClickListener(v -> itemCallback.onOpenDetails(article));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    // 3. SavedNewsViewHolder:
    public static class SavedNewsViewHolder extends RecyclerView.ViewHolder {

        TextView authorTextView;
        TextView descriptionTextView;
        ImageView favoriteIcon;

        public SavedNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            SavedNewsItemBinding binding = SavedNewsItemBinding.bind(itemView);
            authorTextView = binding.savedItemAuthorContent;
            descriptionTextView = binding.savedItemDescriptionContent;
            favoriteIcon = binding.savedItemFavoriteImageView;
        }
    }


}
