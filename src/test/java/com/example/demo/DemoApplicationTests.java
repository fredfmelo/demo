package com.example.demo;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DemoApplicationTests {

    @Mock
    private PersonRepository personRepository;

    private PersonService personService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        personService = new PersonService(personRepository);
    }

    @Test
    public void getPeopleTest() {
        List<Person> toAdd = Stream.of(new Person(UUID.randomUUID(), "Person 1"), new Person(UUID.randomUUID(), "Person 2")).collect(Collectors.toList());
        when(personRepository.findAll()).thenReturn(toAdd);

        assertEquals(2, personService.getPeople().size());
    }

    @Test
    public void getPeopleByNameTest() {
        String name = "Fred Melo";
        Optional<List<Person>> listPerson = Optional.of(Stream.of(new Person(UUID.randomUUID(), name)).collect(Collectors.toList()));
        when(personRepository.findByName(name)).thenReturn(listPerson);

        assertEquals(1, personRepository.findByName(name).get().size());
    }

    @Test
    public void getPersonByIdTest() {
        UUID id = UUID.randomUUID();
        Optional<Person> listPerson = Optional.of((new Person(id, "Fred Melo")));

        when(personRepository.findById(id)).thenReturn(listPerson);

        assertEquals(id, personService.getPersonById(id).get().getId());
    }


    @Test
    public void deletePersonTest() {
        Person p = new Person(UUID.randomUUID(), "Fred Melo");

        personService.deletePerson(p.getId());

        verify(personRepository, times(1)).deleteById(p.getId());
    }


    @Test
    public void updatePersonTest() {
        Person p = new Person(UUID.randomUUID(), "Fred Melo");
        String newName = "John Doe";

        p.setName(newName);
        personService.updatePerson(p);
        when(personService.addPerson(p)).thenReturn(p);

        verify(personRepository, times(1)).save(p);

        assertEquals(newName, personRepository.save(p).getName());
    }

    @Test
    public void addPersonTest() {
        Person p = new Person(UUID.randomUUID(), "Fred Melo");

        when(personService.addPerson(p)).thenReturn(p);

        assertEquals(p.getId(), personRepository.save(p).getId());
    }


}
