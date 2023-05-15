package com.example.alabhiber.service;

import com.example.alabhiber.db.entity.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonService {

    Person save(Person person);

    List<Person> saveAll(List<Person> persons);

    Optional<Person> findById(UUID id);

    List<Person> findByLastName(String lastName);

    List<Person> findAllByDocNumberLike(String pattern);

    List<Person> findAllByActiveDocNumberLike(String pattern);

}
