package com.example.VaxPortal.Exception;

import com.example.VaxPortal.model.Dose;

public class DoseAlreadyTaken extends RuntimeException{
    public DoseAlreadyTaken(String message){
        super(message);
    }
}
