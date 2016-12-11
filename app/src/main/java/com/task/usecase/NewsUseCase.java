package com.task.usecase;

import android.os.Handler;
import android.os.Looper;

import com.task.data.DataRepository;
import com.task.data.remote.ResponseWrapper;
import com.task.data.remote.dto.NewsModel;

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

    public void getScooters(final Callback callback) {
        new Thread(() -> {
            ResponseWrapper responseWrapper = dataRepository.requestScooters();
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

    public interface Callback {
        void onSuccess(NewsModel newsModel);

        void onFail();
    }
}
