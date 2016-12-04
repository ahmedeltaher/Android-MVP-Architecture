package com.task.ui.component.Home;

import android.os.Bundle;

import com.task.data.remote.dto.Scooter;
import com.task.data.remote.dto.ScootersLocationModel;
import com.task.ui.base.Presenter;
import com.task.ui.base.listeners.RecyclerItemListener;
import com.task.usecase.ScootersUseCase;
import com.task.usecase.ScootersUseCase.Callback;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.task.utils.ObjectUtil.isNull;

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
        getView().setLoaderVisibility(true);
        getView().setNoDataVisibility(false);
        getView().setListVisibility(false);
        scootersUseCase.getProducts(callback);
    }

    public RecyclerItemListener getRecyclerItemListener() {
        return recyclerItemListener;
    }

    public ScootersLocationModel getScootersLocationModel() {
        return scootersLocationModel;
    }

    public void onMapClick() {
        getView().navigateToScooterLocator((ArrayList<Scooter>) scootersLocationModel.getData().getScooters());
    }

    private void showList(boolean isVisible) {
        getView().setNoDataVisibility(!isVisible);
        getView().setListVisibility(isVisible);
    }

    private final RecyclerItemListener recyclerItemListener = position -> {
        ArrayList<Scooter> scooters = new ArrayList<>();
        scooters.add(scootersLocationModel.getData().getScooters().get(position));
        getView().navigateToScooterLocator(scooters);
    };

    private final Callback callback = new Callback() {
        @Override
        public void onSuccess(ScootersLocationModel scootersLocationModel) {
            HomePresenter.this.scootersLocationModel = scootersLocationModel;
            List<Scooter> scooters = scootersLocationModel.getData().getScooters();
            if (!isNull(scooters) && !scooters.isEmpty()) {
                getView().initializeScootersList(scootersLocationModel.getData().getScooters());
                showList(true);
            } else {
                showList(false);
            }
            getView().setLoaderVisibility(false);
        }

        @Override
        public void onFail() {
            showList(false);
        }
    };
}
