package com.example.VaxPortal.controller;


import com.example.VaxPortal.Dto.RequestDto.DoctorRequestDto;
import com.example.VaxPortal.Dto.RequestDto.GetAgeByGreaterThanRequestDto;
import com.example.VaxPortal.Dto.ResponseDto.DoctorResponseDto;
import com.example.VaxPortal.Dto.ResponseDto.GetAgeByGreaterThanResponseDto;
import com.example.VaxPortal.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/add_doctor")
    public ResponseEntity addDoctor(@RequestBody DoctorRequestDto doctorRequestDto){
        try {
            DoctorResponseDto doctorResponseDto = doctorService.addDoctor(doctorRequestDto);
            return new ResponseEntity(doctorResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/age_greater_than")
    public ResponseEntity getByAgeGreaterThan(@RequestBody GetAgeByGreaterThanRequestDto getAgeByGreaterThanRequestDto){
        GetAgeByGreaterThanResponseDto doctors = doctorService.getByAgeGreaterThan(getAgeByGreaterThanRequestDto);
        return new ResponseEntity(doctors,HttpStatus.ACCEPTED);
    }

    //get the doctor with highest number of appointment

    // get the list of doctor who belong to particular center

    // update email and/or age of the doctor
}
