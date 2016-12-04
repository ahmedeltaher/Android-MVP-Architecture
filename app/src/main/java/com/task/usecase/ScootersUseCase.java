package com.task.usecase;

import android.os.Handler;
import android.os.Looper;

import com.task.data.DataRepository;
import com.task.data.remote.ResponseWrapper;
import com.task.data.remote.dto.Scooter;
import com.task.data.remote.dto.ScootersLocationModel;

import java.util.Collections;
import java.util.List;

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

    public void getScooters(final Callback callback) {
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

    public void sort(List<Scooter> scooters) {
        sort(scooters, 0, scooters.size() - 1);
    }

    private void sort(List<Scooter> scooters, int from, int to) {
        if (from < to) {
            int pivot = from;
            int left = from + 1;
            int right = to;
            int pivotValue = scooters.get(pivot).getEnergyLevel();
            while (left <= right) {
                // left <= to -> limit protection
                while (left <= to && pivotValue >= scooters.get(left).getEnergyLevel()) {
                    left++;
                }
                // right > from -> limit protection
                while (right > from && pivotValue < scooters.get(right).getEnergyLevel()) {
                    right--;
                }
                if (left > right) {
                    Collections.swap(scooters, right, left);
                }
            }
            Collections.swap(scooters, left - 1, pivot);
            sort(scooters, from, right - 1); // <-- pivot was wront!
            sort(scooters, right + 1, to);   // <-- pivot was wront!
        }
    }

    public interface Callback {
        void onSuccess(ScootersLocationModel scootersLocationModel);

        void onFail();
    }
}
