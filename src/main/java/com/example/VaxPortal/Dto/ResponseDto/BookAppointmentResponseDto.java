package com.example.VaxPortal.Dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookAppointmentResponseDto {

    String personName;

    String DoctorName;

    String appointmentId;

    Date appointementDate;

    CenterResponseDto centerResponseDto;
}
