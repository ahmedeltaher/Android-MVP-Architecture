package com.task.ui.component.ScooterLocation;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.task.App;
import com.task.R;
import com.task.data.remote.dto.Scooter;
import com.task.ui.base.BaseActivity;
import com.task.utils.MapHelper;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by AhmedEltaher on 5/12/2016
 */

public class ScooterLocatorActivity extends BaseActivity implements OnMapReadyCallback, ScooterLocatorView {

    @Inject
    ScooterLocatorPresenter presenter;

    SupportMapFragment mapFragment;

    @Override
    protected void initializeDagger() {
        App app = (App) getApplicationContext();
        app.getMainComponent().inject(ScooterLocatorActivity.this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_maps;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapHelper mapHelper = new MapHelper();
        mapHelper.BuildMarks(googleMap, presenter.getScooters());
    }

    @Override
    public void initializeScootersLocation(List<Scooter> Scooters) {
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
}
