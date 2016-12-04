package com.task.ui.component.ScooterLocation;

import android.os.Bundle;

import com.task.data.remote.dto.Scooter;
import com.task.ui.base.Presenter;

import java.util.List;

import javax.inject.Inject;

import static com.task.utils.Constants.SCOOTERS_KEY;

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
        scooters = (List<Scooter>) extras.get(SCOOTERS_KEY);
    }
}
