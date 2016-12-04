package com.task.ui.component.ScooterLocation;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.task.App;
import com.task.R;
import com.task.data.remote.dto.Scooter;
import com.task.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

public class ScooterLocatorActivity extends BaseActivity implements OnMapReadyCallback, ScooterLocatorView {

    @Inject
    ScooterLocatorPresenter presenter;

    SupportMapFragment mapFragment;

    private GoogleMap map;

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
        map = googleMap;
        // Add a marker in Sydney and move the camera
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        presenter.getScooters();
        for (int i = 0; i < presenter.getScooters().size(); i++) {
            Scooter scooter = presenter.getScooters().get(i);
            LatLng scooterLocation = new LatLng(scooter.getLocation().getLat(), scooter.getLocation().getLng());
            MarkerOptions markerOptions = new MarkerOptions().position(scooterLocation).title(scooter.getLicensePlate());
            int icon;
            if (scooter.getEnergyLevel() > 50) {
                icon = R.drawable.motorcycle_green_pin;
            } else if (scooter.getEnergyLevel() > 30) {
                icon = R.drawable.motorcycle_yellow_pin;
            } else {
                icon = R.drawable.motorcycle_red_pin;
            }
            builder.include(scooterLocation);
            markerOptions.icon(BitmapDescriptorFactory.fromResource(icon));
            map.addMarker(markerOptions);
        }
        LatLngBounds bounds = builder.build();
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (width * 0.10);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);
        map.moveCamera(cameraUpdate);
        map.getUiSettings().isCompassEnabled();
    }

    @Override
    public void initializeScootersLocation(List<Scooter> Scooters) {
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
}
