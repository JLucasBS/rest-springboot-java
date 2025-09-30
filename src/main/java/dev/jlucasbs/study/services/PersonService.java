package dev.jlucasbs.study.services;

import dev.jlucasbs.study.data.dto.PersonDTO;
import dev.jlucasbs.study.exception.ResourceNotFoundException;
import static dev.jlucasbs.study.mapper.ObjectMapper.parseListObjects;
import static dev.jlucasbs.study.mapper.ObjectMapper.parseObject;
import dev.jlucasbs.study.model.Person;
import dev.jlucasbs.study.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository repository;

    public List<PersonDTO> findAll() {
        logger.info("Finding all people");

        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findByID(Long id) {
        logger.info("Finding person by ID {}", id);

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person) {
        logger.info("Creating one person");

        var entity = parseObject(person, Person.class);

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("Updating one person");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(repository.save(entity),  PersonDTO.class);
    }

    public void deleteByID(Long id) {
        logger.info("Deleting person by ID: " + id);

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);
    }
}
