package dev.jlucasbs.study.services;

import dev.jlucasbs.study.controllers.PersonController;
import dev.jlucasbs.study.data.dto.PersonDTO;
import dev.jlucasbs.study.exception.RequiredObjectIsNullException;
import dev.jlucasbs.study.exception.ResourceNotFoundException;
import static dev.jlucasbs.study.mapper.ObjectMapper.parseListObjects;
import static dev.jlucasbs.study.mapper.ObjectMapper.parseObject;
import dev.jlucasbs.study.model.Person;
import dev.jlucasbs.study.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository repository;

    public List<PersonDTO> findAll() {
        logger.info("Finding all people");

        var people = parseListObjects(repository.findAll(), PersonDTO.class);
        people.forEach(this::addHateoasLink);

        return people;
    }

    public PersonDTO findByID(Long id) {
        logger.info("Finding person by ID {}", id);

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        var dto = parseObject(entity, PersonDTO.class);
        addHateoasLink(dto);

        return dto;
    }

    public PersonDTO create(PersonDTO person) {
        logger.info("Creating one person");

        if (person == null) throw new RequiredObjectIsNullException();

        var entity = parseObject(person, Person.class);

        var dto = parseObject(repository.save(entity), PersonDTO.class);
        addHateoasLink(dto);

        return dto;
    }

    public PersonDTO update(Long id, PersonDTO person) {
        logger.info("Updating one person");

        if (person == null) throw new RequiredObjectIsNullException();

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto = parseObject(repository.save(entity),  PersonDTO.class);
        addHateoasLink(dto);
        return dto;
    }

    public void deleteByID(Long id) {
        logger.info("Deleting a person by ID: {}", id);

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);
    }

    @Transactional
    public PersonDTO disablePerson(Long id) {
        logger.info("Disabling a person by ID: {}", id);

        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        repository.disablePerson(id);

        return parseObject(repository.findById(id).get(), PersonDTO.class);
    }

    private void addHateoasLink(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findByID(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto.getId(), dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).disablePerson(dto.getId())).withRel("disable").withType("PATCH"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
