package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getPeople() {
        return personService.getPeople();
    }

    @PostMapping
    public void addPerson(@Valid @NotNull @RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping(path = "/{name}")
    public List<Person> getPersonByName(@PathVariable("name") String name) {
        return personService.getPeopleByName(name).orElse(new ArrayList<>());
    }

    @GetMapping(path = "/id/{id}")
    public Person getPersonById(@PathVariable("id") UUID id) throws Exception {
        return personService.getPersonById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePerson(@PathVariable("id") UUID id) {
        personService.deletePerson(id);
    }

    @PutMapping
    public void updatePerson(@Valid @NotNull @RequestBody Person person) {
        personService.updatePerson(person);
    }


}
