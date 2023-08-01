package com.example.VaxPortal.Dto.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetAgeByGreaterThanResponseDto {

    List<String> Doctors = new ArrayList<>();
}
