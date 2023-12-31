package com.example.VaxPortal.Dto.RequestDto;

import com.example.VaxPortal.Enumerator.DoseType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDoseRequestDto {

    int personId;

    DoseType doseType;
}
