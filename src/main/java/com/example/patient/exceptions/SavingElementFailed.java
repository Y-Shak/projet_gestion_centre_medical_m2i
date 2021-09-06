package com.example.patient.exceptions;

public class SavingElementFailed extends Exception{
    public SavingElementFailed() {
    }

    public SavingElementFailed(String message) {
        super(message);
    }

    public SavingElementFailed(String message, Throwable cause) {
        super(message, cause);
    }

    public SavingElementFailed(Throwable cause) {
        super(cause);
    }

    public SavingElementFailed(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
