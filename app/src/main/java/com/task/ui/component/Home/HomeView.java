package com.task.ui.component.Home;

import com.task.data.remote.dto.Scooter;
import com.task.ui.base.Presenter;

import java.util.List;

/**
 * Created by AhmedEltaher on 25/11/2016
 */

public interface HomeView extends Presenter.View {
    void initializeScootersList(List<Scooter> scooters);

    void setLoaderVisiblity(boolean isVisible);

    void navigateToScooterLocator(List<Scooter> scooters);
}
