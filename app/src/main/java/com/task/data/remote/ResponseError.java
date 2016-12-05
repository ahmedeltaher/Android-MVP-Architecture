package com.task.data.remote;

/**
 * Created by AhmedEltaher on 5/12/2016
 */

public class ResponseError {

    private String errorMessage;
    private int errorCode;

    public ResponseError(String errorMessage, int errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
