package com.laioffer.tinnews.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.laioffer.tinnews.model.Article;
import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.repository.NewsRepository;

public class HomeViewModel extends ViewModel {
    private final NewsRepository repository;
    private final MutableLiveData<String> countryInput = new MutableLiveData<>();

    public HomeViewModel(NewsRepository newsRepository) {
        this.repository = newsRepository;
    }
    //view 来 call 这个function
    public void setCountryInput(String country) {
        countryInput.setValue(country);
    }
    //view会盯着和这个data看
    public LiveData<NewsResponse> getTopHeadlines() {
        return Transformations.switchMap(countryInput, repository::getTopHeadlines);
    }
    //the API to favorite an article
    public void setFavoriteArticleInput(Article article) {
        repository.favoriteArticle(article);
    }
}
