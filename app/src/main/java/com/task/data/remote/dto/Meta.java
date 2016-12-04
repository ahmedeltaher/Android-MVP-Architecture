
package com.task.data.remote.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Meta implements Parcelable {
    @SerializedName("server_time")
    private String serverTime;
    @SerializedName("status")
    private Integer status;
    @SerializedName("key")
    private String key;

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.serverTime);
        dest.writeValue(this.status);
        dest.writeString(this.key);
    }

    public Meta() {
    }

    protected Meta(Parcel in) {
        this.serverTime = in.readString();
        this.status = (Integer) in.readValue(Integer.class.getClassLoader());
        this.key = in.readString();
    }

    public static final Parcelable.Creator<Meta> CREATOR = new Parcelable.Creator<Meta>() {
        @Override
        public Meta createFromParcel(Parcel source) {
            return new Meta(source);
        }

        @Override
        public Meta[] newArray(int size) {
            return new Meta[size];
        }
    };
}
