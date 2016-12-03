
package com.task.data.remote.dto;

import com.google.gson.annotations.SerializedName;

public class ScootersLocationModel {

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

}
