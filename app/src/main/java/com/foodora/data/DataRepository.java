package com.foodora.data;

import com.foodora.data.local.LocalRepository;
import com.foodora.data.remote.ApiRepository;
import com.foodora.data.remote.ResponseWrapper;

import javax.inject.Inject;

/**
 * Created by AhmedEltaher on 25/11/2016
 */

public class DataRepository {
    private ApiRepository apiRepository;
    private LocalRepository localRepository;

    @Inject
    public DataRepository(ApiRepository apiRepository, LocalRepository localRepository) {
        this.apiRepository = apiRepository;
        this.localRepository = localRepository;
    }

    public ResponseWrapper requestFoodoraProducts() {
        ResponseWrapper response = apiRepository.getFoodoraProducts();
        return response;
    }
}
