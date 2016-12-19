package com.task.usecase;

import android.os.Handler;
import android.os.Looper;

import com.task.data.DataRepository;
import com.task.data.remote.ResponseWrapper;
import com.task.data.remote.dto.NewsItem;
import com.task.data.remote.dto.NewsModel;

import java.util.List;

import javax.inject.Inject;

import static com.task.utils.NetworkUtils.isSuccess;

/**
 * Created by AhmedEltaher on 5/12/2016
 */

public class NewsUseCase {
    DataRepository dataRepository;

    @Inject
    public NewsUseCase(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void getNews(final Callback callback) {
        new Thread(() -> {
            ResponseWrapper responseWrapper = dataRepository.requestNews();
            new Handler(Looper.getMainLooper()).post(() -> {
                if (isSuccess(responseWrapper.getCode())) {
                    NewsModel newsModel = (NewsModel) responseWrapper.getResponse();
                    callback.onSuccess(newsModel);
                } else {
                    callback.onFail();
                }
            });
        }).start();
    }

    public NewsItem searchByTitle(List<NewsItem> news, String keyWord) {
        for (NewsItem newsItem : news) {
            if (newsItem.getTitle().toLowerCase().contains(keyWord.toLowerCase())) {
                return newsItem;
            }
        }
        return null;
    }

    public interface Callback {
        void onSuccess(NewsModel newsModel);

        void onFail();
    }
}
