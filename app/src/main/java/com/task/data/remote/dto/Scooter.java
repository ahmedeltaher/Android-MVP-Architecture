
package com.task.data.remote.dto;

import com.google.gson.annotations.SerializedName;

public class Scooter {
    @SerializedName("id")
    private String id;
    @SerializedName("vin")
    private String vin;
    @SerializedName("model")
    private String model;
    @SerializedName("license_plate")
    private String licensePlate;
    @SerializedName("energy_level")
    private Integer energyLevel;
    @SerializedName("location")
    private Location location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Integer getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(Integer energyLevel) {
        this.energyLevel = energyLevel;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
