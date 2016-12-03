
package com.task.data.remote.dto;

import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("lng")
    private Double lng;
    @SerializedName("lat")
    private Double lat;

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

}
