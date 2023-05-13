package com.example.alabhiber.service.impl;

import com.example.alabhiber.db.entity.Person;
import com.example.alabhiber.db.repository.PersonRepository;
import com.example.alabhiber.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonServiceJPAImpl implements PersonService {
    private final PersonRepository personRepository;

    @Override
    public Person save(Person person) {
        linkDoc(person);
        return personRepository.save(person);
    }

    @Override
    public List<Person> saveAll(List<Person> persons) {
        persons.forEach(this::linkDoc);
        return personRepository.saveAll(persons);
    }

    @Override
    public Optional<Person> findById(UUID id) {
        return personRepository.findById(id);
    }

    @Override
    public List<Person> findByLastName(String lastName) {
        return personRepository.findByLastName(lastName);
    }

    @Override
    public List<Person> findAllByDocNumberLike(String pattern) {
        return personRepository.findAllByDocNumLike(pattern);
    }

    @Override
    public List<Person> findAllByActiveDocNumberLike(String pattern) {
        return personRepository.findAllByActiveDocNumLike(pattern);
    }

    private void linkDoc(Person p) {
        p.getDocuments().stream().filter(o -> Objects.isNull(o.getPerson()))
                .forEach(o -> o.setPerson(p));
    }
}
