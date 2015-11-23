package com.bhati.exception;

/**
 * Created by esuchug on 30-06-2015.
 */
public class ValidationException extends Exception {


    String message;

    int errorCode;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public ValidationException(MDAResponse response){
        super(response.getMessage());
        this.message = response.getMessage();
        this.errorCode = response.getErrorCode();


    }


}
