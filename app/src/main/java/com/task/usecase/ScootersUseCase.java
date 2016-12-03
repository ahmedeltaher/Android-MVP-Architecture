package com.task.usecase;

import android.os.Handler;
import android.os.Looper;

import com.task.data.DataRepository;
import com.task.data.remote.ResponseWrapper;
import com.task.data.remote.dto.ScootersLocationModel;

import javax.inject.Inject;

import static com.task.utils.NetworkUtils.isSuccess;

/**
 * Created by AhmedEltaher on 25/11/2016
 */

public class ScootersUseCase {
    DataRepository dataRepository;

    @Inject
    public ScootersUseCase(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void getProducts(final Callback callback) {
        new Thread(() -> {
            ResponseWrapper responseWrapper = dataRepository.requestScooters();
            new Handler(Looper.getMainLooper()).post(() -> {
                if (isSuccess(responseWrapper.getCode())) {
                    ScootersLocationModel scootersLocationModel = (ScootersLocationModel) responseWrapper.getResponse();
                    callback.onSuccess(scootersLocationModel);
                } else {
                    callback.onFail();
                }
            });
        }).start();
    }

    public interface Callback {
        void onSuccess(ScootersLocationModel scootersLocationModel);

        void onFail();
    }
}
