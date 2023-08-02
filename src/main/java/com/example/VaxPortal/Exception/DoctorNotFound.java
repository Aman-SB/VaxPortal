package com.example.VaxPortal.Exception;

public class DoctorNotFound extends RuntimeException{

    public DoctorNotFound(String message){
        super(message);
    }
}
