package com.example.patient.exceptions;

public class ErrorChargingOneElementData extends Exception{
    public ErrorChargingOneElementData() {
    }

    public ErrorChargingOneElementData(String message) {
        super(message);
    }

    public ErrorChargingOneElementData(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorChargingOneElementData(Throwable cause) {
        super(cause);
    }

    public ErrorChargingOneElementData(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
