package dev.jlucasbs.study.integrationTests.controllers.withjson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jlucasbs.study.config.TestConfigs;
import dev.jlucasbs.study.integrationTests.dto.PersonDTO;
import dev.jlucasbs.study.integrationTests.testContainers.AbstractIntegrationTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonControllerJsonTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;

    private static PersonDTO person;

    @BeforeAll
    static void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        person = new PersonDTO();
    }

    @Test
    @Order(1)
    void create() throws JsonProcessingException {
        mockPerson();
        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_GOOGLE)
                .setBasePath("/api/v1/person")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        String content = given(specification)
                .contentType(ContentType.JSON)
                .body(person)
                .when()
                    .post()
                .then()
                    .statusCode(200)
                .extract()
                    .body().asString();

        PersonDTO createdPerson = objectMapper.readValue(content, PersonDTO.class);

        person = createdPerson;

        assertNotNull(createdPerson.getId());
        assertTrue(createdPerson.getId() > 0);

        assertEquals("John", createdPerson.getFirstName());
        assertEquals("Doe", createdPerson.getLastName());
        assertEquals("New York City - New York - USA", createdPerson.getAddress());
        assertEquals("Male", createdPerson.getGender());
        assertTrue(createdPerson.getEnabled());
    }

    @Test
    @Order(2)
    void update() throws JsonProcessingException {
        person.setLastName("Albert Wesker");

        String content = given(specification)
                .contentType(ContentType.JSON)
                .pathParam("id", person.getId())
                .body(person)
                .when()
                .put("{id}")
                .then()
                .statusCode(200)
                .extract()
                .body().asString();

        PersonDTO foundPerson = objectMapper.readValue(content, PersonDTO.class);

        assertNotNull(foundPerson.getId());
        assertTrue(foundPerson.getId() > 0);

        assertEquals("John", foundPerson.getFirstName());
        assertEquals("Albert Wesker", foundPerson.getLastName());
        assertEquals("New York City - New York - USA", foundPerson.getAddress());
        assertEquals("Male", foundPerson.getGender());
        assertTrue(foundPerson.getEnabled());
    }

    @Test
    @Order(3)
    void findByID() throws JsonProcessingException {
        String content = given(specification)
                .contentType(ContentType.JSON)
                .pathParam("id", person.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(200)
                .extract()
                .body().asString();

        PersonDTO foundPerson = objectMapper.readValue(content, PersonDTO.class);

        assertNotNull(foundPerson.getId());
        assertTrue(foundPerson.getId() > 0);

        assertEquals("John", foundPerson.getFirstName());
        assertEquals("Albert Wesker", foundPerson.getLastName());
        assertEquals("New York City - New York - USA", foundPerson.getAddress());
        assertEquals("Male", foundPerson.getGender());
        assertTrue(foundPerson.getEnabled());
    }

    @Test
    @Order(4)
    void disable() throws JsonProcessingException {
        String content = given(specification)
                .contentType(ContentType.JSON)
                .pathParam("id", person.getId())
                .when()
                .patch("{id}")
                .then()
                .statusCode(200)
                .extract()
                .body().asString();

        PersonDTO foundPerson = objectMapper.readValue(content, PersonDTO.class);

        assertNotNull(foundPerson.getId());
        assertTrue(foundPerson.getId() > 0);

        assertEquals("John", foundPerson.getFirstName());
        assertEquals("Albert Wesker", foundPerson.getLastName());
        assertEquals("New York City - New York - USA", foundPerson.getAddress());
        assertEquals("Male", foundPerson.getGender());
        assertFalse(foundPerson.getEnabled());
    }

    @Test
    @Order(5)
    void delete() throws JsonProcessingException {
        given(specification)
                .pathParam("id", person.getId())
                .when()
                .delete("{id}")
                .then()
                .statusCode(204);
    }

    @Test
    @Order(6)
    void findAll() throws JsonProcessingException {
        String content = given(specification)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body().asString();

        List<PersonDTO> people = objectMapper.readValue(content, new TypeReference<List<PersonDTO>>() {});

        PersonDTO personOne = people.get(0);

        assertNotNull(personOne.getId());
        assertTrue(personOne.getId() > 0);

        assertEquals("Alice", personOne.getFirstName());
        assertEquals("Johnson", personOne.getLastName());
        assertEquals("123 Maple Street, Springfield", personOne.getAddress());
        assertEquals("Female", personOne.getGender());
        assertTrue(personOne.getEnabled());

        PersonDTO personFour = people.get(3);

        assertNotNull(personFour.getId());
        assertTrue(personFour.getId() > 0);

        assertEquals("Diego", personFour.getFirstName());
        assertEquals("Martinez", personFour.getLastName());
        assertEquals("890 Sunset Blvd, Los Angeles", personFour.getAddress());
        assertEquals("Male", personFour.getGender());
        assertTrue(personFour.getEnabled());
    }

    private void mockPerson() {
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress("New York City - New York - USA");
        person.setGender("Male");
        person.setEnabled(true);
    }
}
