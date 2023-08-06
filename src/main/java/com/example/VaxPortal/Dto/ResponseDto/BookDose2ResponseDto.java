package com.example.VaxPortal.Dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDose2ResponseDto extends BookDoseResponseDto{

    String personName;

    String certificateNo;

    String certificateMsg;
}
