package com.example.VaxPortal.service;

import com.example.VaxPortal.Dto.RequestDto.BookDose1RequestDto;
import com.example.VaxPortal.Exception.DoseAlreadyTaken;
import com.example.VaxPortal.Exception.PersonNotFound;
import com.example.VaxPortal.model.Dose;
import com.example.VaxPortal.model.Person;
import com.example.VaxPortal.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DoseService {

    @Autowired
    PersonRepository personRepository;

    public Dose getDose1(BookDose1RequestDto bookDose1RequestDto) {
        Optional<Person> optionalPerson = personRepository.findById(bookDose1RequestDto.getPersonId());

        if(!optionalPerson.isPresent()){
            throw new PersonNotFound("Invalid Person Id");
        }

        //checking for dose1 is taken or not
        Person person = optionalPerson.get();
        if(person.isDose1Taken()){
            throw new DoseAlreadyTaken("Dose 1 is Already Taken");
        }

        //create a dose from request dto
        Dose dose = new Dose();
        dose.setDoseId(String.valueOf(UUID.randomUUID()));
        dose.setDoseType(bookDose1RequestDto.getDoseType());
        dose.setPerson(person);

        person.setDose1Taken(true);
        person.getDosesTaken().add(dose);
        Person savedPerson = personRepository.save(person);
        return savedPerson.getDosesTaken().get(0);
    }
}
