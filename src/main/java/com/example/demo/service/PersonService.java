package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPeople() {
        return personRepository.findAll();
    }

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    public void updatePerson(Person person) {
        personRepository.save(person);
    }

    public void deletePerson(UUID id) {
        personRepository.deleteById(id);
    }

    public Optional<List<Person>> getPeopleByName(String name) {
        return personRepository.findByName(name);
    }

    public Optional<Person> getPersonById(UUID id) {
        return personRepository.findById(id);
    }

    public void deleteAllPersons() {
        personRepository.deleteAll();
    }

    private List<Person> getFakePeople() {
        return Stream.of(new Person(UUID.randomUUID(), "Fred Melo"), new Person(UUID.randomUUID(), "João da Silva")).collect(Collectors.toList());
    }


}
