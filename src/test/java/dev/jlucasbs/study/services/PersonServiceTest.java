package dev.jlucasbs.study.services;

import dev.jlucasbs.study.data.dto.PersonDTO;
import dev.jlucasbs.study.exception.RequiredObjectIsNullException;
import dev.jlucasbs.study.model.Person;
import dev.jlucasbs.study.repository.PersonRepository;
import dev.jlucasbs.study.uniteTests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService service;

    @Mock
    private PersonRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockPerson();

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByID() {
        Person person = input.mockEntity(1);
        person.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));

        var result = service.findByID(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(person.getId(), result.getId());
        assertNotNull(result.getLinks());

        assertEquals("Address Test1",  result.getAddress());
        assertEquals("First Name Test1",  result.getFirstName());
        assertEquals("Last Name Test1",  result.getLastName());
        assertEquals("Female",  result.getGender());

    }

    @Test
    void create() {
        Person person = input.mockEntity(1);
        person.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        when(repository.save(person)).thenReturn(person);

        var result = service.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(person.getId(), result.getId());
        assertNotNull(result.getLinks());

        assertEquals("Address Test1",  result.getAddress());
        assertEquals("First Name Test1",  result.getFirstName());
        assertEquals("Last Name Test1",  result.getLastName());
        assertEquals("Female",  result.getGender());
    }

    @Test
    void testCreateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> service.create(null));

        String expectedMessage = "Person object must not be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void update() {
        Person person = input.mockEntity(1);
        person.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(person));
        when(repository.save(person)).thenReturn(person);

        var result = service.update(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertEquals(person.getId(), result.getId());

        assertEquals("Address Test1",  result.getAddress());
        assertEquals("First Name Test1",  result.getFirstName());
        assertEquals("Last Name Test1",  result.getLastName());
        assertEquals("Female",  result.getGender());
    }

    @Test
    void testUpdateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> service.update(null));

        String expectedMessage = "Person object must not be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void deleteByID() {
        Person person = input.mockEntity(1);
        person.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));

        service.deleteByID(1L);
        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(Person.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findAll() {
        List<Person> list = input.mockEntityList();

        when(repository.findAll()).thenReturn(list);

        List<PersonDTO> people = service.findAll();

        assertNotNull(people);
        assertEquals(14, people.size());

        var personOne = people.get(1);

        assertNotNull(personOne);
        assertNotNull(personOne.getId());
        assertNotNull(personOne.getLinks());

        assertEquals("Address Test1",  personOne.getAddress());
        assertEquals("First Name Test1",  personOne.getFirstName());
        assertEquals("Last Name Test1",  personOne.getLastName());
        assertEquals("Female",  personOne.getGender());

        var personFour = people.get(4);

        assertNotNull(personFour);
        assertNotNull(personFour.getId());
        assertNotNull(personFour.getLinks());

        assertEquals("Address Test4",  personFour.getAddress());
        assertEquals("First Name Test4",  personFour.getFirstName());
        assertEquals("Last Name Test4",  personFour.getLastName());
        assertEquals("Male",  personFour.getGender());

        var personSeven = people.get(7);

        assertNotNull(personSeven);
        assertNotNull(personSeven.getId());
        assertNotNull(personSeven.getLinks());

        assertEquals("Address Test7",  personSeven.getAddress());
        assertEquals("First Name Test7",  personSeven.getFirstName());
        assertEquals("Last Name Test7",  personSeven.getLastName());
        assertEquals("Female",  personSeven.getGender());
    }
}