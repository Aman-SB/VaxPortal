package com.example.VaxPortal.Dto.ResponseDto;

import com.example.VaxPortal.Enumerator.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DoctorResponseDto {

    String name;

    String message;

    CenterResponseDto centerResponseDto;

}
