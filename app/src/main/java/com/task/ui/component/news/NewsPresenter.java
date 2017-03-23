package com.task.ui.component.news;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;

import com.task.data.remote.dto.NewsItem;
import com.task.data.remote.dto.NewsModel;
import com.task.ui.base.BasePresenter;
import com.task.ui.base.listeners.BaseCallback;
import com.task.ui.base.listeners.RecyclerItemListener;
import com.task.usecase.NewsUseCase;

import java.util.List;

import javax.inject.Inject;

import static com.task.utils.ObjectUtil.isEmpty;
import static com.task.utils.ObjectUtil.isNull;

/**
 * Created by AhmedEltaher on 5/12/2016
 */

public class NewsPresenter extends BasePresenter<NewsContract.View> implements NewsContract.Presenter {

    private final NewsUseCase newsUseCase;
    private NewsModel newsModel;

    @Inject
    public NewsPresenter(NewsUseCase newsUseCase) {
        this.newsUseCase = newsUseCase;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        getNews();
    }

    @Override
    public void getNews() {
        getView().setLoaderVisibility(true);
        getView().setNoDataVisibility(false);
        getView().setListVisibility(false);
        getView().incrementCountingIdlingResource();
        newsUseCase.getNews(callback);
    }

    @Override
    public RecyclerItemListener getRecyclerItemListener() {
        return recyclerItemListener;
    }

    @Override
    public void onSearchClick(String newsTitle) {
        List<NewsItem> news = newsModel.getNewsItems();
        if (!isEmpty(newsTitle) && !isNull(news) && !news.isEmpty()) {
            NewsItem newsItem = newsUseCase.searchByTitle(news, newsTitle);
            if (!isNull(newsItem)) {
                getView().navigateToDetailsScreen(newsItem);
            } else {
                getView().showSearchError();
            }
        } else {
            getView().showSearchError();
        }
    }

    private void showList(boolean isVisible) {
        getView().setNoDataVisibility(!isVisible);
        getView().setListVisibility(isVisible);
    }

    private final RecyclerItemListener recyclerItemListener = position -> {
        getView().navigateToDetailsScreen(newsModel.getNewsItems().get(position));
    };

    private void onGetNewsSuccess(NewsModel newsModel) {
        getView().decrementCountingIdlingResource();
        NewsPresenter.this.newsModel = newsModel;
        List<NewsItem> newsItems = newsModel.getNewsItems();
        if (!isNull(newsItems) && !newsItems.isEmpty()) {
            getView().initializeNewsList(newsModel.getNewsItems());
            showList(true);
        } else {
            showList(false);
        }
        getView().setLoaderVisibility(false);
    }


    private void onGetNewsFailed() {
        getView().decrementCountingIdlingResource();
        showList(false);
        getView().setLoaderVisibility(false);
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    public NewsModel getNewsModel() {
        return newsModel;
    }

    private final BaseCallback callback = new BaseCallback() {
        @Override
        public void onSuccess(NewsModel newsModel) {
            onGetNewsSuccess(newsModel);
        }

        @Override
        public void onFail() {
            onGetNewsFailed();
        }
    };

}
