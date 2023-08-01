package com.example.VaxPortal.service;

import com.example.VaxPortal.Dto.RequestDto.CenterRequestDto;
import com.example.VaxPortal.Dto.ResponseDto.CenterResponseDto;
import com.example.VaxPortal.model.VaccinationCenter;
import com.example.VaxPortal.repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationCenterServices {

    @Autowired
    VaccinationCenterRepository centerRepository;
    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto) {

        //request dto -> entity
        VaccinationCenter vaccinationCenter = new VaccinationCenter();
        vaccinationCenter.setCenterType(centerRequestDto.getCenterType());
        vaccinationCenter.setCenterName(centerRequestDto.getCenterName());
        vaccinationCenter.setAddress(centerRequestDto.getAddress());

        VaccinationCenter savedCenter = centerRepository.save(vaccinationCenter);

        //entity -> response

        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setCenterName(savedCenter.getCenterName());
        centerResponseDto.setAddress(savedCenter.getAddress());
        centerResponseDto.setAddress(savedCenter.getAddress());

        return centerResponseDto;
    }
}
