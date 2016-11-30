package com.test.data.remote.service;

import com.test.data.remote.dto.Products;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by AhmedEltaher on 25/11/2016
 */

public interface ProductService {
    @GET("514884a074f872f8cdc30ab71a4703df/raw/68c3ac46cf715829f275796f75ed5c414b2d2c8d/foodora-sample-products.json")
    Call<Products> fetchProducts();
}
