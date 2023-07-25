package com.example.VaxPortal.Dto.RequestDto;

import com.example.VaxPortal.Enumerator.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddPersonRequestDto {
    String name;

    int age;

    String emailId;

    Gender gender;
}
