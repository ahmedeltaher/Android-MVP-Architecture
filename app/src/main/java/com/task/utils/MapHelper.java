package com.task.utils;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.task.R;
import com.task.data.remote.dto.Scooter;

import java.util.List;

import static com.task.App.getContext;
import static com.task.utils.Constants.FINE_ENERGY;
import static com.task.utils.Constants.PADDING_FACTOR;
import static com.task.utils.Constants.WARING_ENERGY;
import static com.task.utils.ObjectUtil.isNull;

/**
 * Created by AhmedEltaher on 05/12/16.
 */

public class MapHelper {
    public GoogleMap BuildMarks(GoogleMap googleMap, List<Scooter> scooters) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        if (!isNull(scooters) && !scooters.isEmpty()) {
            for (int i = 0; i < scooters.size(); i++) {
                Scooter scooter = scooters.get(i);
                LatLng scooterLocation = new LatLng(scooter.getLocation().getLat(), scooter.getLocation().getLng());
                MarkerOptions markerOptions = new MarkerOptions().position(scooterLocation).title(scooter.getLicensePlate());
                int icon;
                if (scooter.getEnergyLevel() > FINE_ENERGY) {
                    icon = R.drawable.motorcycle_green_pin;
                } else if (scooter.getEnergyLevel() > WARING_ENERGY) {
                    icon = R.drawable.motorcycle_yellow_pin;
                } else {
                    icon = R.drawable.motorcycle_red_pin;
                }
                builder.include(scooterLocation);
                markerOptions.icon(BitmapDescriptorFactory.fromResource(icon));
                googleMap.addMarker(markerOptions);
            }
        }
        LatLngBounds bounds = builder.build();
        int width = getContext().getResources().getDisplayMetrics().widthPixels;
        int height = getContext().getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (width * PADDING_FACTOR);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);
        googleMap.moveCamera(cameraUpdate);
        googleMap.getUiSettings().isCompassEnabled();
        return googleMap;
    }
}
