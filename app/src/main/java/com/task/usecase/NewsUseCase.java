package com.task.usecase;

import android.os.Handler;
import android.os.Looper;

import com.task.data.DataRepository;
import com.task.data.remote.ServiceResponse;
import com.task.data.remote.dto.NewsItem;
import com.task.data.remote.dto.NewsModel;
import com.task.threadPool.DefaultExecutorSupplier;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Inject;

import static com.task.data.remote.ServiceError.isSuccess;
import static com.task.threadPool.DefaultExecutorSupplier.getDefaultExecutorSupplier;
import static com.task.utils.ObjectUtil.isNull;

/**
 * Created by AhmedEltaher on 5/12/2016
 */

public class NewsUseCase {
    DataRepository dataRepository;
    Handler handler;
    ThreadPoolExecutor threadPoolExecutor;

    @Inject
    public NewsUseCase(DataRepository dataRepository, Handler handler, ThreadPoolExecutor threadPoolExecutor) {
        this.dataRepository = dataRepository;
        this.handler = handler;
        this.threadPoolExecutor = threadPoolExecutor;
    }

    public void getNews(final Callback callback) {
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
