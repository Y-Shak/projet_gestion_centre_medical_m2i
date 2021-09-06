package com.example.patient.exceptions;

public class ErrorChargingData extends Exception{
    public ErrorChargingData() {
    }

    public ErrorChargingData(String message) {
        super(message);
    }

    public ErrorChargingData(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorChargingData(Throwable cause) {
        super(cause);
    }

    public ErrorChargingData(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
