package com.example.VaxPortal.repository;

import com.example.VaxPortal.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
    Person findByEmailId(String oldEmailId);

    @Query(value = "select * from person where age>:age And gender='MALE'",nativeQuery = true)
    List<Person> getAllMalesGreaterThan(int age);

    @Query(value = "select * from person where gender='FEMALE' And dose1taken=true And dose2taken=false",nativeQuery = true)
    List<Person> getAllFemalesTakenOnlyDose1();

    @Query(value = "select * from person where dose1taken=true And dose2taken=true",nativeQuery = true)
    List<Person> getAllPeopleWhoAreFullyVaccinated();

    @Query(value = "select * from person where dose1taken=false And dose2taken=false",nativeQuery = true)
    List<Person> getAllWhoHaveNotTakenASingleDose();

    @Query(value = "select * from person where age>:age And gender='FEMALE' And dose1taken=true And dose2Taken=false",nativeQuery = true)
    List<Person> getFemalesWhoseAgeIsGreaterThanAndTakenDose1(int age);

    @Query(value = "select * from person where age>:age And gender='MALE' And dose1taken=true And dose2taken=true",nativeQuery = true)
    List<Person> getMalesWhoseAgeIsGreaterThanAndTakenBothDoses(int age);

}
