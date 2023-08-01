package com.example.VaxPortal.controller;

import com.example.VaxPortal.Dto.RequestDto.CenterRequestDto;
import com.example.VaxPortal.Dto.ResponseDto.CenterResponseDto;
import com.example.VaxPortal.service.VaccinationCenterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/center")
public class VaccinationCenterController {
    @Autowired
    VaccinationCenterServices centerService;

    @PostMapping("/add_center")
    public CenterResponseDto addCenter(@RequestBody CenterRequestDto centerRequestDto){
        CenterResponseDto centerResponseDto = centerService.addCenter(centerRequestDto);
        return centerResponseDto;
    }

    //get all the doctors at center of particular centerType

    //get the center with the highest number of doctor

    //get the center with highest number of doctors among a particular centerType



}
