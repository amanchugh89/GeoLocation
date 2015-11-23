package com.bhati.exception;

/**
 * Created by esuchug on 27-06-2015.
 */
public enum MDAResponse {

    OK("OK",0),
    NO_RESPONSE("No Response Received- check logs",1001);

    private final String message;
    private final int errorCode;

    MDAResponse(String message,int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
