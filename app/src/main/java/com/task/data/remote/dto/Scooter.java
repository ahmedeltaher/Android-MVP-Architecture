
package com.task.data.remote.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Scooter implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.vin);
        dest.writeString(this.model);
        dest.writeString(this.licensePlate);
        dest.writeValue(this.energyLevel);
        dest.writeParcelable(this.location, flags);
    }

    public Scooter() {
    }

    protected Scooter(Parcel in) {
        this.id = in.readString();
        this.vin = in.readString();
        this.model = in.readString();
        this.licensePlate = in.readString();
        this.energyLevel = (Integer) in.readValue(Integer.class.getClassLoader());
        this.location = in.readParcelable(Location.class.getClassLoader());
    }

    public static final Parcelable.Creator<Scooter> CREATOR = new Parcelable.Creator<Scooter>() {
        @Override
        public Scooter createFromParcel(Parcel source) {
            return new Scooter(source);
        }

        @Override
        public Scooter[] newArray(int size) {
            return new Scooter[size];
        }
    };
}
