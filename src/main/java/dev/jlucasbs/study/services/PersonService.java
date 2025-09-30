package dev.jlucasbs.study.services;

import dev.jlucasbs.study.exception.ResourceNotFoundException;
import dev.jlucasbs.study.model.Person;
import dev.jlucasbs.study.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository repository;

    public List<Person> findAll() {
        logger.info("Finding all people");

        return repository.findAll();
    }

    public Person findByID(Long id) {
        logger.info("Finding person by ID: " + id);

        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    }

    public Person create(Person person) {
        logger.info("Creating one person");

        return repository.save(person);
    }

    public Person update(Person person) {
        logger.info("Updating one person");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }

    public void deleteByID(Long id) {
        logger.info("Deleting person by ID: " + id);

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);
    }
}
