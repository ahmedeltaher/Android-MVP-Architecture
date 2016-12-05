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

import static android.text.TextUtils.isEmpty;
import static com.task.utils.ObjectUtil.isNull;

/**
 * Created by AhmedEltaher on 5/12/2016
 */

public class HomePresenter extends Presenter<HomeView> {

    private final ScootersUseCase scootersUseCase;
    private ScootersLocationModel scootersLocationModel;
    private List<Scooter> scooters;

    @Inject
    public HomePresenter(ScootersUseCase scootersUseCase) {
        this.scootersUseCase = scootersUseCase;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        getScooters();
    }

    public void getScooters() {
        getView().setLoaderVisibility(true);
        getView().setNoDataVisibility(false);
        getView().setListVisibility(false);
        scootersUseCase.getScooters(callback);
    }

    public RecyclerItemListener getRecyclerItemListener() {
        return recyclerItemListener;
    }

    public void onMapClick() {
        if (!isNull(scooters) && !scooters.isEmpty()) {
            getView().navigateToScooterLocator((ArrayList<Scooter>) scooters);
        } else {
            getView().showMenuMapError();
        }
    }

    public void onSearchClick(String licensePlate) {
        if (!isEmpty(licensePlate)) {
            Scooter scooter = scootersUseCase.findScooterByLicensePlate(licensePlate, scooters);
            if (!isNull(scooter)) {
                ArrayList<Scooter> scooters = new ArrayList<>();
                scooters.add(scooter);
                getView().navigateToScooterLocator(scooters);
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
        ArrayList<Scooter> scooters = new ArrayList<>();
        scooters.add(scootersLocationModel.getData().getScooters().get(position));
        getView().navigateToScooterLocator(scooters);
    };

    private final Callback callback = new Callback() {
        @Override
        public void onSuccess(ScootersLocationModel scootersLocationModel) {
            HomePresenter.this.scootersLocationModel = scootersLocationModel;
            scooters = scootersLocationModel.getData().getScooters();
            if (!isNull(scooters) && !scooters.isEmpty()) {
                scootersUseCase.sort(scooters);
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
            getView().setLoaderVisibility(false);
        }
    };
}
