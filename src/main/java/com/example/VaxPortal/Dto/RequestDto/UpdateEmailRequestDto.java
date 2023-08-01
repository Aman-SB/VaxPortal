package com.example.VaxPortal.Dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateEmailRequestDto {

    String oldEmailId;

    String newEmailId;
}
