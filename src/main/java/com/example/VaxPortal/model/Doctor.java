package com.example.VaxPortal.model;

import com.example.VaxPortal.Enumerator.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    int age;

    @Column(unique = true)
    String emailId;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @OneToMany(mappedBy = "doctor" , cascade = CascadeType.ALL)
    List<Appointment> appointments = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    VaccinationCenter center;
}
