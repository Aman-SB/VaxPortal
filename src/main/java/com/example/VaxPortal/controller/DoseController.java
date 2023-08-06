package com.example.VaxPortal.controller;

import com.example.VaxPortal.Dto.RequestDto.BookDoseRequestDto;
import com.example.VaxPortal.Dto.ResponseDto.BookDoseResponseDto;
import com.example.VaxPortal.service.DoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dose")
public class DoseController {

    @Autowired
    DoseService doseService;

    @PostMapping("/get_dose_1")
    public ResponseEntity getDose1(@RequestBody BookDoseRequestDto bookDoseRequestDto){
        try{
            BookDoseResponseDto bookDoseResponseDto = doseService.getDose1(bookDoseRequestDto);
            return new ResponseEntity(bookDoseResponseDto,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //dose2

    @PostMapping("/get_dose_1")
    public ResponseEntity getDose2(@RequestBody BookDoseRequestDto bookDoseRequestDto){
        try{
            BookDoseResponseDto bookDose2ResponseDto = doseService.getDose2(bookDoseRequestDto);
            return new ResponseEntity(bookDose2ResponseDto,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
