package com.example.demo.commandlinerunners;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PersoCommandLineRunner implements CommandLineRunner {

    @Autowired
    private PersonService personService;

    private static final Logger log = LoggerFactory.getLogger(PersoCommandLineRunner.class);

    @Override
    public void run(String... args) throws Exception {
//        personService.addPerson(new Person(null, "Nova Pessoa"));
//        personService.deleteAllPersons();


//        System.out.println(personService.getPersonById(UUID.fromString("672def59-2048-4eaf-a9a9-9ddcc7c3efce")));

//        System.out.println(personService.getPeople());

    }
}
