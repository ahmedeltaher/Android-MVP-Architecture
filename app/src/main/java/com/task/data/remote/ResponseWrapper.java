package com.task.data.remote;

/**
 * Created by AhmedEltaher on 25/11/2016
 */

public class ResponseWrapper {
    private int code;
    private Object response;
    private ResponseError error;

    public ResponseWrapper(int code, Object response) {
        this.code = code;
        this.response = response;
    }

    public ResponseWrapper(ResponseError error) {
        this.error = error;
    }

    public ResponseWrapper(Object response) {
        this.response = response;
    }

    public int getCode() {
        return code;
    }

    public ResponseError getError() {
        return error;
    }

    public Object getResponse() {

        return response;
    }
}
