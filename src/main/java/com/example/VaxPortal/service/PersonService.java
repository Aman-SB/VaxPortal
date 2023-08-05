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

import java.util.ArrayList;
import java.util.List;

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

    //get all males greater than a certain age;
    public List<AddPersonResponseDto> getAllMalesGreaterThan(int age) {
        List<Person> persons=personRepository.getAllMalesGreaterThan(age);
        List<AddPersonResponseDto> personResponseDto=new ArrayList<>();

        //Convert Entity -> Response Dto
        for (Person p:persons)
        {
            AddPersonResponseDto addPersonResponseDto=new AddPersonResponseDto();
            addPersonResponseDto.setName(p.getName());
            addPersonResponseDto.setMessage("Age Greater than:-"+age);
            personResponseDto.add(addPersonResponseDto);
        }
        return personResponseDto;
    }

    //get all females who have taken only dose 1 not dose 2;
    public List<AddPersonResponseDto> getAllFemalesTakenOnlyDose1() {
        List<Person> persons=personRepository.getAllFemalesTakenOnlyDose1();
        List<AddPersonResponseDto> personResponseDtos=new ArrayList<>();

        //Convert Entity -> Response Dto
        for (Person p:persons)
        {
            AddPersonResponseDto addPersonResponseDto=new AddPersonResponseDto();
            addPersonResponseDto.setName(p.getName());
            addPersonResponseDto.setMessage("Female taken only Dose 1.");
            personResponseDtos.add(addPersonResponseDto);
        }
        return personResponseDtos;
    }

    //get all people who are fully vaccinated;
    public List<AddPersonResponseDto> getAllPeopleWhoAreFullyVaccinated() {
        List<Person> persons=personRepository.getAllPeopleWhoAreFullyVaccinated();
        List<AddPersonResponseDto> addPersonResponseDtos=new ArrayList<>();

        //Convert Entity -> Response Dto
        for (Person p:persons)
        {
            AddPersonResponseDto addPersonResponseDto=new AddPersonResponseDto();
            addPersonResponseDto.setName(p.getName());
            addPersonResponseDto.setMessage("Fully Vaccinated Person.");
            addPersonResponseDtos.add(addPersonResponseDto);
        }
        return addPersonResponseDtos;
    }

    //get all people who have not taken even a single dose;
    public List<AddPersonResponseDto> getAllWhoHaveNotTakenASingleDose() {
        List<Person> persons=personRepository.getAllWhoHaveNotTakenASingleDose();
        List<AddPersonResponseDto> addPersonResponseDtos=new ArrayList<>();

        //Convert Entity to Response Dto
        for (Person p:persons)
        {
            AddPersonResponseDto addPersonResponseDto=new AddPersonResponseDto();
            addPersonResponseDto.setName(p.getName());
            addPersonResponseDto.setMessage("Not taken a Single Dose.");
            addPersonResponseDtos.add(addPersonResponseDto);
        }
        return addPersonResponseDtos;
    }

    //get all females whose age is greater than a particular age and who have taken only dose 1;
    public List<AddPersonResponseDto> getFemalesWhoseAgeIsGreaterThanAndTakenDose1(int age) {
        List<Person> persons=personRepository.getFemalesWhoseAgeIsGreaterThanAndTakenDose1(age);
        List<AddPersonResponseDto> addPersonResponseDtos=new ArrayList<>();

        //Convert Entity -> Response Dto
        for (Person p:persons)
        {
            AddPersonResponseDto addPersonResponseDto=new AddPersonResponseDto();
            addPersonResponseDto.setName(p.getName());
            addPersonResponseDto.setMessage("Female's age is greater than "+age+" and taken only Dose1.");
            addPersonResponseDtos.add(addPersonResponseDto);
        }
        return addPersonResponseDtos;
    }

    //get all males whose age is greater than a particular age and who have taken both doses;
    public List<AddPersonResponseDto> getMalesWhoseAgeIsGreaterThanAndTakenBothDoses(int age) {
        List<Person> persons=personRepository.getMalesWhoseAgeIsGreaterThanAndTakenBothDoses(age);
        List<AddPersonResponseDto> addPersonResponseDtos=new ArrayList<>();

        //Convert Entity -> Response Dto
        for (Person p:persons)
        {
            AddPersonResponseDto addPersonResponseDto=new AddPersonResponseDto();
            addPersonResponseDto.setName(p.getName());
            addPersonResponseDto.setMessage("Male's age is Greater than "+age+" and taken both Doses");
            addPersonResponseDtos.add(addPersonResponseDto);
        }
        return addPersonResponseDtos;
    }

}
