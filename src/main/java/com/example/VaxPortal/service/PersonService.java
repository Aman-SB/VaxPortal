package com.example.VaxPortal.service;

import com.example.VaxPortal.Dto.RequestDto.AddPersonRequestDto;
import com.example.VaxPortal.Dto.RequestDto.UpdateEmailRequestDto;
import com.example.VaxPortal.Dto.ResponseDto.AddPersonResponseDto;
import com.example.VaxPortal.Dto.ResponseDto.UpdateEmailResponseDto;
import com.example.VaxPortal.Exception.PersonNotFound;
import com.example.VaxPortal.model.Person;
import com.example.VaxPortal.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;


    //add person
    public AddPersonResponseDto addPerson(AddPersonRequestDto addPersonRequestDto) {

        // convert request dto -> entity
        Person person = new Person();
        person.setName(addPersonRequestDto.getName());
        person.setAge(addPersonRequestDto.getAge());
        person.setEmailId(addPersonRequestDto.getEmailId());
        person.setGender(addPersonRequestDto.getGender());

        //save to db
        Person saveResponse = personRepository.save(person);

        // save entity -> response dto
        AddPersonResponseDto addPersonResponseDto = new AddPersonResponseDto();
        addPersonResponseDto.setName(saveResponse.getName());
        addPersonResponseDto.setMessage("Congrats! Yous have been registered");

        return addPersonResponseDto;
    }

    //updateEmail
    public UpdateEmailResponseDto updateEmail(UpdateEmailRequestDto updateEmailRequestDto) {
        Person person = personRepository.findByEmailId(updateEmailRequestDto.getOldEmailId());
        if(person == null){
            throw new PersonNotFound("Sorry! we can't find the person with this emailId");
        }

        person.setEmailId(updateEmailRequestDto.getNewEmailId());
        personRepository.save(person);

        UpdateEmailResponseDto updateEmailResponseDto = new UpdateEmailResponseDto();
        updateEmailResponseDto.setMessage("Congrats! Your emailId has been Changed");

        return updateEmailResponseDto;
    }


}
