package dev.jlucasbs.study.services;

import dev.jlucasbs.study.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {
    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    public List<Person> findAll() {
        logger.info("Finding all people");

        List<Person> persons = new ArrayList<>();

        for (int i = 1; i <= 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }

    public Person findByID(String id) {
        logger.info("Finding person by ID: " + id);

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("João");
        person.setLastName("Lucas");
        person.setAddress("Leme - São Paulo - Brasil");
        person.setGender("Male");

        return person;
    }

    public Person create(Person person) {
        logger.info("Creating one person");

        return person;
    }

    public Person update(Person person) {
        logger.info("Updating one person");

        return person;
    }

    public void deleteByID(String id) {
        logger.info("Deleting person by ID: " + id);
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Firstname " + i);
        person.setLastName("Lastname " + i);
        person.setAddress("Some Address " + i);
        person.setGender("Male");

        return person;
    }
}
