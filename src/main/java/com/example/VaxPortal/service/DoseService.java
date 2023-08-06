package com.example.VaxPortal.service;

import com.example.VaxPortal.Dto.RequestDto.BookDoseRequestDto;
import com.example.VaxPortal.Dto.ResponseDto.BookDose2ResponseDto;
import com.example.VaxPortal.Dto.ResponseDto.BookDoseResponseDto;
import com.example.VaxPortal.Enumerator.DoseType;
import com.example.VaxPortal.Exception.Dose1NotTakenException;
import com.example.VaxPortal.Exception.DoseAlreadyTaken;
import com.example.VaxPortal.Exception.PersonNotFound;
import com.example.VaxPortal.model.Certificate;
import com.example.VaxPortal.model.Dose;
import com.example.VaxPortal.model.Person;
import com.example.VaxPortal.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoseService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public BookDoseResponseDto getDose1(BookDoseRequestDto bookDoseRequestDto) {
        Optional<Person> optionalPerson = personRepository.findById(bookDoseRequestDto.getPersonId());

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
        dose.setDoseType(bookDoseRequestDto.getDoseType());
        dose.setPerson(person);

        person.setDose1Taken(true);
        person.getDosesTaken().add(dose);
        Person savedPerson = personRepository.save(person);
        Dose dose1 = savedPerson.getDosesTaken().get(0);

        BookDoseResponseDto bookDoseResponseDto = new BookDoseResponseDto();
        bookDoseResponseDto.setMessage("Dose1 is taken");
        return bookDoseResponseDto;
    }

    public BookDoseResponseDto getDose2(BookDoseRequestDto bookDoseRequestDto) {
        Optional<Person> optionalPerson = personRepository.findById(bookDoseRequestDto.getPersonId());

        if(!optionalPerson.isPresent()){
            throw new PersonNotFound("Invalid Person Id");
        }

        Person person = optionalPerson.get();

        if(!person.isDose1Taken()) {
            throw new Dose1NotTakenException("Sorry! you have not taken dose1 Please, ensure to take Dose1 first then Dose2");
        }

        if(person.isDose2Taken()) {
            throw new DoseAlreadyTaken("Dose 2 already taken");
        }

        Dose dose = new Dose();

        // getting dose1 type so that dose2 can be provided with the same type
        List<Dose> doseList= person.getDosesTaken(); // get list of dose taken for person and get latest dose and their type
        Dose latestDose = doseList.get(doseList.size()-1);
        DoseType doseType = latestDose.getDoseType();

        dose.setDoseId(String.valueOf(UUID.randomUUID()));
        person.setDose2Taken(true);
        dose.setDoseType(doseType);
        person.getDosesTaken().add(dose);
        dose.setPerson(person);

        Person savedPerson = personRepository.save(person);

        Certificate certificate = new Certificate();
        certificate.setCertificateNo(String.valueOf(UUID.randomUUID()));
        certificate.setConfirmationMessage("Certificate! generated for dose 2.");

        // sending mail to the person who have done their dose 1
        String text = "Congratulation!" + person.getName() + "you have fully vaccinated with dose type "+doseType+ " with dose Id " + savedPerson.getId() + " please keep this auto generated certificate for proof or authenticity with certificate no " + certificate.getCertificateNo();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("rockeykumarsoftengg@gmail.com");
        simpleMailMessage.setSubject("Dose 2 vaccination done and you are fully vaccinated!.");
        simpleMailMessage.setTo(person.getEmailId());
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);

        BookDose2ResponseDto bookDose2ResponseDto = new BookDose2ResponseDto();
        bookDose2ResponseDto.setPersonName(person.getName());
        bookDose2ResponseDto.setCertificateNo(certificate.getCertificateNo());
        bookDose2ResponseDto.setCertificateMsg(certificate.getConfirmationMessage());
        bookDose2ResponseDto.setMessage("Congratulation! "+person.getName() + " you have fully vaccinated with dose type "+doseType);

        return bookDose2ResponseDto;
    }
}
