package com.task.ui.component.Home;

import com.task.data.remote.dto.Scooter;
import com.task.ui.base.Presenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AhmedEltaher on 25/11/2016
 */

public interface HomeView extends Presenter.View {
    void initializeScootersList(List<Scooter> scooters);

    void setLoaderVisibility(boolean isVisible);

    void navigateToScooterLocator(ArrayList<Scooter> scooters);

    void setNoDataVisibility(boolean isVisible);

    void setListVisibility(boolean isVisible);

    void showSearchError();

    void showMenuMapError();
}
