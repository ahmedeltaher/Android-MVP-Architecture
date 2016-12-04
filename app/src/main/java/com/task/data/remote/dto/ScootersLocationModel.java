
package com.task.data.remote.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ScootersLocationModel implements Parcelable {

    @SerializedName("meta")
    private Meta meta;
    @SerializedName("data")
    private Data data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.meta, flags);
        dest.writeParcelable(this.data, flags);
    }

    public ScootersLocationModel() {
    }

    protected ScootersLocationModel(Parcel in) {
        this.meta = in.readParcelable(Meta.class.getClassLoader());
        this.data = in.readParcelable(Data.class.getClassLoader());
    }

    public static final Parcelable.Creator<ScootersLocationModel> CREATOR = new Parcelable.Creator<ScootersLocationModel>() {
        @Override
        public ScootersLocationModel createFromParcel(Parcel source) {
            return new ScootersLocationModel(source);
        }

        @Override
        public ScootersLocationModel[] newArray(int size) {
            return new ScootersLocationModel[size];
        }
    };
}
