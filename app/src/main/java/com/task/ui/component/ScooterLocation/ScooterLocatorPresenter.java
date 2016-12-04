package com.task.ui.component.ScooterLocation;

import android.os.Bundle;

import com.task.data.remote.dto.Scooter;
import com.task.ui.base.Presenter;
import com.task.utils.L;

import java.util.List;

import javax.inject.Inject;

import static com.task.utils.Constants.SCOOTERS_KEY;
import static java.lang.String.valueOf;

/**
 * Created by AhmedEltaher on 25/11/2016
 */

public class ScooterLocatorPresenter extends Presenter<ScooterLocatorView> {

    private List<Scooter> scooters;

    @Inject
    public ScooterLocatorPresenter() {
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        scooters = extras.getParcelableArrayList(SCOOTERS_KEY);
        L.d(valueOf(scooters.size()));
        getView().initializeScootersLocation(scooters);
    }

    public List<Scooter> getScooters() {
        return scooters;
    }
}
