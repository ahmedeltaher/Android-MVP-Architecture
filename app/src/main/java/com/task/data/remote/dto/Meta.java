
package com.task.data.remote.dto;

import com.google.gson.annotations.SerializedName;

public class Meta {
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

}
