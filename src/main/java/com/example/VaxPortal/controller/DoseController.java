package com.example.VaxPortal.controller;

import com.example.VaxPortal.Dto.RequestDto.BookDose1RequestDto;
import com.example.VaxPortal.Enumerator.DoseType;
import com.example.VaxPortal.model.Dose;
import com.example.VaxPortal.repository.DoseRepository;
import com.example.VaxPortal.service.DoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dose")
public class DoseController {

    @Autowired
    DoseService doseService;

    @PostMapping("/get_dose_1")
    public ResponseEntity getDose1(@RequestBody BookDose1RequestDto bookDose1RequestDto){
        try{
            Dose doseTake = doseService.getDose1(bookDose1RequestDto);
            return new ResponseEntity(doseTake,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //dose2


}
