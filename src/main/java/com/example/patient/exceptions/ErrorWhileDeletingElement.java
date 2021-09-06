package com.example.patient.exceptions;

public class ErrorWhileDeletingElement extends Exception{
    public ErrorWhileDeletingElement() {
    }

    public ErrorWhileDeletingElement(String message) {
        super(message);
    }

    public ErrorWhileDeletingElement(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorWhileDeletingElement(Throwable cause) {
        super(cause);
    }

    public ErrorWhileDeletingElement(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
