package com.task.data.remote;

import android.support.annotation.NonNull;

import com.task.App;
import com.task.data.remote.dto.ScootersLocationModel;
import com.task.data.remote.service.ScootersLocatorService;
import com.task.utils.Constants;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

import static com.task.utils.Constants.ERROR_UNDEFINED;
import static com.task.utils.NetworkUtils.ERROR_NO_INTERNET;
import static com.task.utils.NetworkUtils.NETWORK_ERROR;
import static com.task.utils.NetworkUtils.isNetworkAvailable;
import static com.task.utils.ObjectUtil.isNull;

/**
 * Created by AhmedEltaher on 5/12/2016
 */

public class ApiRepository {
    private ServiceGenerator serviceGenerator;

    @Inject
    public ApiRepository(ServiceGenerator serviceGenerator) {
        this.serviceGenerator = serviceGenerator;
    }

    public ResponseWrapper getScooters() {
        ScootersLocatorService scootersLocatorService = serviceGenerator.createService(ScootersLocatorService.class, Constants.BASE_URL);
        Call<ScootersLocationModel> call = scootersLocatorService.fetchScooters();
        return processCall(call);
    }

    //Process the calls
    @NonNull
    private ResponseWrapper processCall(Call call) {
        if (!isNetworkAvailable(App.getContext())) {
            return new ResponseWrapper(new ResponseError("", ERROR_NO_INTERNET));
        }
        return processResponse(call, false);
    }

    @NonNull
    private ResponseWrapper processResponse(Call call, boolean isVoid) {
        try {
            Response response = call.execute();
            if (isNull(response)) {
                //Extra check in case internet is disconnected in between or no proper response
                // received from backend
                return new ResponseWrapper(new ResponseError(NETWORK_ERROR, ERROR_UNDEFINED));
            }
            int responseCode = response.code();
            if (response.isSuccessful()) {
                return new ResponseWrapper(responseCode, isVoid ? null : response.body());
            } else {
                ResponseError responseError;
                responseError = new ResponseError(response.message(), responseCode);
                return new ResponseWrapper(responseError);
            }
        } catch (IOException e) {
            return new ResponseWrapper(new ResponseError(NETWORK_ERROR, ERROR_UNDEFINED));
        }
    }
}
