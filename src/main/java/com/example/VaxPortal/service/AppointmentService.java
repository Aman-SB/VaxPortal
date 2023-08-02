package com.example.VaxPortal.service;

import com.example.VaxPortal.Dto.RequestDto.BookAppointmentRequestDto;
import com.example.VaxPortal.Dto.ResponseDto.BookAppointmentResponseDto;
import com.example.VaxPortal.Dto.ResponseDto.CenterResponseDto;
import com.example.VaxPortal.Exception.DoctorNotFound;
import com.example.VaxPortal.Exception.PersonNotFound;
import com.example.VaxPortal.model.Appointment;
import com.example.VaxPortal.model.Doctor;
import com.example.VaxPortal.model.Person;
import com.example.VaxPortal.model.VaccinationCenter;
import com.example.VaxPortal.repository.AppointmentRepository;
import com.example.VaxPortal.repository.DoctorRepository;
import com.example.VaxPortal.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public BookAppointmentResponseDto bookAppointment(BookAppointmentRequestDto bookAppointmentRequestDto) {
        // check for both person and doctor

        Optional<Person> optionalPerson = personRepository.findById(bookAppointmentRequestDto.getPersonId());

        if(!optionalPerson.isPresent()){
            throw new PersonNotFound("Person is not Valid");
        }

        Optional<Doctor> optionalDoctor = doctorRepository.findById(bookAppointmentRequestDto.getDoctorId());

        if (!optionalDoctor.isPresent()){
            throw new DoctorNotFound("Docotr is not Valid");
        }

        Person person = optionalPerson.get();
        Doctor doctor = optionalDoctor.get();

        //create an appointment object
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(String.valueOf(UUID.randomUUID()));
        appointment.setPerson(person);
        appointment.setDoctor(doctor);

        Appointment savedAppointment = appointmentRepository.save(appointment);

        doctor.getAppointments().add(savedAppointment);
        person.getAppointments().add(savedAppointment);

        Doctor savedDoctor = doctorRepository.save(doctor);
        Person savedPerson = personRepository.save(person);
        VaccinationCenter center = savedDoctor.getCenter();

        //send an email
        String text = "Congrats! " + savedPerson.getName() + "your appointment have been booked with doctor " +
                savedDoctor.getName() + "Your vaccination Center Name is : " + center.getCenterName() + "Please reach at this address "
                + center.getAddress() + "at this time: " + savedAppointment.getAppointmentDate() + "Thank You! ";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("orcahude@gmail.com");
        simpleMailMessage.setTo(savedPerson.getEmailId());
        simpleMailMessage.setSubject(text);

        javaMailSender.send(simpleMailMessage);

        // prepare the response dto
        BookAppointmentResponseDto bookAppointmentResponseDto = new BookAppointmentResponseDto();
        bookAppointmentResponseDto.setPersonName(savedPerson.getName());
        bookAppointmentResponseDto.setDoctorName(savedDoctor.getName());
        bookAppointmentResponseDto.setAppointmentId(savedAppointment.getAppointmentId());
        bookAppointmentResponseDto.setAppointementDate(savedAppointment.getAppointmentDate());

        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setCenterName(center.getCenterName());
        centerResponseDto.setAddress(center.getAddress());
        centerResponseDto.setAddress(center.getAddress());

        return bookAppointmentResponseDto;
    }
}
