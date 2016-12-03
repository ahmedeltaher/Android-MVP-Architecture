package com.task.usecase;

import android.os.Handler;
import android.os.Looper;

import com.task.data.DataRepository;
import com.task.data.remote.ResponseWrapper;
import com.task.data.remote.dto.Products;

import javax.inject.Inject;

import static com.task.utils.NetworkUtils.isSuccess;

/**
 * Created by AhmedEltaher on 25/11/2016
 */

public class FoodoraProductsUseCase {
    DataRepository dataRepository;

    @Inject
    public FoodoraProductsUseCase(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void getProducts(final Callback callback) {
        new Thread(() -> {
            ResponseWrapper responseWrapper = dataRepository.requestFoodoraProducts();
            new Handler(Looper.getMainLooper()).post(() -> {
                if (isSuccess(responseWrapper.getCode())) {
                    Products products = (Products) responseWrapper.getResponse();
                    callback.onSuccess(products);
                } else {
                    callback.onFail();
                }
            });
        }).start();
    }

    public interface Callback {
        void onSuccess(Products products);

        void onFail();
    }
}
