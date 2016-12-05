package com.task.data.remote.service;

import com.task.data.remote.dto.ScootersLocationModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by AhmedEltaher on 5/12/2016
 */

public interface ScootersLocatorService {
    @GET("/api/v1/scooters.json")
    Call<ScootersLocationModel> fetchScooters();
}
