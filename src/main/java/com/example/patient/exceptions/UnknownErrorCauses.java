package com.example.patient.exceptions;

public class UnknownErrorCauses extends Exception{
    public UnknownErrorCauses() {
    }

    public UnknownErrorCauses(String message) {
        super(message);
    }

    public UnknownErrorCauses(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownErrorCauses(Throwable cause) {
        super(cause);
    }

    public UnknownErrorCauses(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
