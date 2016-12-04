package com.task.ui.component.ScooterLocation;

import com.task.data.remote.dto.Scooter;
import com.task.ui.base.Presenter;

import java.util.List;

/**
 * Created by AhmedEltaher on 25/11/2016
 */

public interface ScooterLocatorView extends Presenter.View {
    void initializeScootersLocation(List<Scooter> Scooters);
}
