package com.laioffer.tinnews.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.tinnews.R;
import com.laioffer.tinnews.databinding.SearchNewsItemBinding;
import com.laioffer.tinnews.model.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchNewsAdapter extends RecyclerView.Adapter<SearchNewsAdapter.SearchNewsViewHolder> {
    //define an ItemCallback interface, and a private instance of the listener, and a setter
    // for navigation from SearchFragment to DetailsFragment.
    //define interface
    interface ItemCallback {
        void onOpenDetails(Article article);
    }
//定义变量
    private ItemCallback itemCallback;
//创建setter
    public void setItemCallback(ItemCallback itemCallback) {
        this.itemCallback = itemCallback;
    }


    // 1. Supporting data:In our case, it’s a list of Articles.
    private List<Article> articles = new ArrayList<>();

    public void setArticles(List<Article> newsList) {
        articles.clear();
        articles.addAll(newsList);
        //every time a new list is set, we call notifyDataSetChanged to let the adapter refresh and re-render the data.
        notifyDataSetChanged();
    }



    // 2. Adapter overrides:
    @NonNull
    @Override
    //providing the generated item views
    public SearchNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.search_news_item, parent, false);
        return new SearchNewsViewHolder(view);

    }

    @Override
    //binding the data with a view
    public void onBindViewHolder(@NonNull SearchNewsViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.favoriteImageView.setImageResource(R.drawable.ic_favorite_24dp);
        holder.itemTitleTextView.setText(article.title);
        //用Picasso展示图片
        Picasso.get().load(article.urlToImage).into(holder.itemImageView);
        //binding完,用户点击。跳转到detail页面
        holder.itemView.setOnClickListener(v -> itemCallback.onOpenDetails(article));
    }

    @Override
    //providing the current data collection size
    public int getItemCount() {
        return articles.size();
    }

    // 3. SearchNewsViewHolder:holding the view references.
    public static class SearchNewsViewHolder extends RecyclerView.ViewHolder {

        ImageView favoriteImageView;
        ImageView itemImageView;
        TextView itemTitleTextView;

        public SearchNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            SearchNewsItemBinding binding = SearchNewsItemBinding.bind(itemView);
            favoriteImageView = binding.searchItemFavorite;
            itemImageView = binding.searchItemImage;
            itemTitleTextView = binding.searchItemTitle;
        }
    }


}
