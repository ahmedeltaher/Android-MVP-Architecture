package com.task.ui.component.Home;

import android.os.Bundle;

import com.task.data.remote.dto.ScootersLocationModel;
import com.task.ui.base.Presenter;
import com.task.ui.base.listeners.RecyclerItemListener;
import com.task.usecase.ScootersUseCase;
import com.task.usecase.ScootersUseCase.Callback;

import javax.inject.Inject;

/**
 * Created by AhmedEltaher on 25/11/2016
 */

public class HomePresenter extends Presenter<HomeView> {

    private final ScootersUseCase scootersUseCase;
    private ScootersLocationModel scootersLocationModel;

    @Inject
    public HomePresenter(ScootersUseCase scootersUseCase) {
        this.scootersUseCase = scootersUseCase;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        scootersUseCase.getProducts(callback);
    }

    public RecyclerItemListener getRecyclerItemListener() {
        return recyclerItemListener;
    }


    public ScootersLocationModel getScootersLocationModel() {
        return scootersLocationModel;
    }


    private final RecyclerItemListener recyclerItemListener = position -> {
        //TODO goto Scootor Location
        if (isViewAlive.get()) {
        }
    };

    private final Callback callback = new Callback() {
        @Override
        public void onSuccess(ScootersLocationModel scootersLocationModel) {
            if (isViewAlive.get()) {
                HomePresenter.this.scootersLocationModel = scootersLocationModel;
                getView().initializeScootersList(scootersLocationModel.getData().getScooters());
                getView().setLoaderVisiblity(false);
            }
        }

        @Override
        public void onFail() {

        }
    };
}
