package com.example.patient.exceptions;

public class InvalidFiledData extends Exception{
    public InvalidFiledData() {
    }

    public InvalidFiledData(String message) {
        super(message);
    }

    public InvalidFiledData(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFiledData(Throwable cause) {
        super(cause);
    }

    public InvalidFiledData(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
