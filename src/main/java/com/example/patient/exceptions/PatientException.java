package com.example.patient.exceptions;

public class PatientException extends Exception{
    public PatientException(String message){
        super(" Exception Patient : " + message);
    }
}
