package com.task.usecase;

import android.os.Handler;

import com.task.data.DataRepository;
import com.task.data.remote.ServiceResponse;
import com.task.data.remote.dto.NewsItem;
import com.task.data.remote.dto.NewsModel;
import com.task.ui.base.listeners.BaseCallback;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Inject;

import static com.task.data.remote.ServiceError.isSuccess;
import static com.task.utils.ObjectUtil.isNull;

/**
 * Created by AhmedEltaher on 5/12/2016
 */

public class NewsUseCase implements UseCase {
    DataRepository dataRepository;
    Handler handler;
    ThreadPoolExecutor threadPoolExecutor;

    @Inject
    public NewsUseCase(DataRepository dataRepository, Handler handler, ThreadPoolExecutor threadPoolExecutor) {
        this.dataRepository = dataRepository;
        this.handler = handler;
        this.threadPoolExecutor = threadPoolExecutor;
    }

    @Override
    public void getNews(final BaseCallback callback) {
        Runnable runnableTask = () -> {
            ServiceResponse serviceResponse = dataRepository.requestNews();
            handler.post(() -> {
                if (!isNull(serviceResponse) && isSuccess(serviceResponse.getCode())) {
                    NewsModel newsModel = (NewsModel) serviceResponse.getData();
                    callback.onSuccess(newsModel);
                } else {
                    callback.onFail();
                }
            });
        };
        threadPoolExecutor.execute(runnableTask);
    }

    @Override
    public NewsItem searchByTitle(List<NewsItem> news, String keyWord) {
        for (NewsItem newsItem : news) {
            if (newsItem.getTitle().toLowerCase().contains(keyWord.toLowerCase())) {
                return newsItem;
            }
        }
        return null;
    }
}
