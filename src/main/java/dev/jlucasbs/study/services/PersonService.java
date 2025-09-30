package dev.jlucasbs.study.services;

import dev.jlucasbs.study.model.Person;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {
    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

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
}
