package com.example.VaxPortal.controller;

import com.example.VaxPortal.Dto.RequestDto.AddPersonRequestDto;
import com.example.VaxPortal.Dto.RequestDto.UpdateEmailRequestDto;
import com.example.VaxPortal.Dto.ResponseDto.AddPersonResponseDto;
import com.example.VaxPortal.Dto.ResponseDto.UpdateEmailResponseDto;
import com.example.VaxPortal.model.Person;
import com.example.VaxPortal.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping("/add_person")
    public ResponseEntity addPerson(@RequestBody AddPersonRequestDto addPersonRequestDto){
        try{
            AddPersonResponseDto personResponse = personService.addPerson(addPersonRequestDto);
         return new ResponseEntity(personResponse,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity("Invalid", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update_email")
    public ResponseEntity updateEmail(@RequestBody UpdateEmailRequestDto updateEmailRequestDto){
        try{
            UpdateEmailResponseDto updateEmailResponseDto = personService.updateEmail(updateEmailRequestDto);
            return new ResponseEntity(updateEmailResponseDto,HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //get all the male greater than a particular age

    // get all females who have taken only dose1 not dose 2

    //get all the people who are fully vaccinated

    //get all the people who have not taken a single dose

    //get all the female greater than a particular age and who have taken only 1 dose

    //get all the male who are greater than particular age and taken both dose
}
