package com.example.VaxPortal.Exception;

public class PersonNotFound extends RuntimeException{

    public PersonNotFound(String message){
        super(message);
    }
}
