package com.task.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by AhmedEltaher on 06/11/16.
 */

public class NetworkUtils {

    private static final int GROUP_200 = 2;
    private static final int GROUP_400 = 4;
    private static final int GROUP_500 = 5;
    private static final int VALUE_100 = 100;
    public static final int ERROR_NO_INTERNET = 3;
    public static final String NETWORK_ERROR = "Unknown Error";


    public static boolean isSuccess(int responseCode) {
        return responseCode / VALUE_100 == GROUP_200;
    }

    public static boolean isError400(int errorCode) {
        return errorCode / VALUE_100 == GROUP_400;
    }

    public static boolean isError500(int errorCode) {
        return errorCode / VALUE_100 == GROUP_500;
    }

    public static boolean isNetworkAvailable(final Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        //should check null because in airplane mode it will be null
        return (networkInfo != null && networkInfo.isConnected());
    }
}

