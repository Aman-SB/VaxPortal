package com.example.VaxPortal.service;

import com.example.VaxPortal.Dto.RequestDto.DoctorRequestDto;
import com.example.VaxPortal.Dto.RequestDto.GetAgeByGreaterThanRequestDto;
import com.example.VaxPortal.Dto.ResponseDto.CenterResponseDto;
import com.example.VaxPortal.Dto.ResponseDto.DoctorResponseDto;
import com.example.VaxPortal.Dto.ResponseDto.GetAgeByGreaterThanResponseDto;
import com.example.VaxPortal.Exception.CenterNotFound;
import com.example.VaxPortal.model.Doctor;
import com.example.VaxPortal.model.VaccinationCenter;
import com.example.VaxPortal.repository.DoctorRepository;
import com.example.VaxPortal.repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    VaccinationCenterRepository centerRepository;

    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) {

        Optional<VaccinationCenter> optionalVaccinationCenter = centerRepository.findById(doctorRequestDto.getCenterId());

        if(!optionalVaccinationCenter.isPresent()){
            throw new CenterNotFound("Invalid Center Id");
        }

        VaccinationCenter vaccinationCenter = optionalVaccinationCenter.get();

        // request -> entity

        Doctor doctor = new Doctor();

        doctor.setName(doctorRequestDto.getName());
        doctor.setAge(doctorRequestDto.getAge());
        doctor.setEmailId(doctorRequestDto.getEmailId());
        doctor.setGender(doctorRequestDto.getGender());
        doctor.setCenter(vaccinationCenter);

        //add in center
        vaccinationCenter.getDoctors().add(doctor);

        VaccinationCenter savedCenter = centerRepository.save(vaccinationCenter); // save both center and doctor

        //entity -> response dto
        List<Doctor> doctors = savedCenter.getDoctors();
        Doctor latestSavedDoctor = doctors.get(doctors.size()-1);

        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setCenterName(savedCenter.getCenterName());
        centerResponseDto.setCenterType(savedCenter.getCenterType());
        centerResponseDto.setAddress(savedCenter.getAddress());

        DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
        doctorResponseDto.setName(latestSavedDoctor.getName());
        doctorResponseDto.setMessage("Congrats! you have been registered");
        doctorResponseDto.setCenterResponseDto(centerResponseDto);

        return doctorResponseDto;
    }

    public GetAgeByGreaterThanResponseDto getByAgeGreaterThan(GetAgeByGreaterThanRequestDto getAgeByGreaterThanRequestDto) {

        List<Doctor> doctorList = doctorRepository.getAgeGreaterThan(getAgeByGreaterThanRequestDto.getAge());

        GetAgeByGreaterThanResponseDto getAgeByGreaterThanResponseDto = new GetAgeByGreaterThanResponseDto();

        for(Doctor doctor : doctorList){
            getAgeByGreaterThanResponseDto.getDoctors().add(doctor.getName());
        }

        return getAgeByGreaterThanResponseDto;
    }
}

