package com.task.ui.component.ScooterLocation;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.task.App;
import com.task.R;
import com.task.data.remote.dto.Scooter;
import com.task.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class ScooterLocatorActivity extends BaseActivity implements OnMapReadyCallback, ScooterLocatorView {

    @Inject
    ScooterLocatorPresenter presenter;

    @Bind(R.id.map)
    SupportMapFragment mapFragment;

    private GoogleMap mMap;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void openMapView() {

    }

    @Override
    public void openHomeView() {

    }

    @Override
    public void initializeScootersLocation(List<Scooter> Scooters) {
        mapFragment.getMapAsync(this);
    }
}
