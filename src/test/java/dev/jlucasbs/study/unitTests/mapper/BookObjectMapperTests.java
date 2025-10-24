package dev.jlucasbs.study.unitTests.mapper;

import dev.jlucasbs.study.data.dto.BookDTO;
import dev.jlucasbs.study.model.Book;
import dev.jlucasbs.study.unitTests.mapper.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static dev.jlucasbs.study.mapper.ObjectMapper.parseListObjects;
import static dev.jlucasbs.study.mapper.ObjectMapper.parseObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookObjectMapperTests {
    MockBook inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockBook();
    }

    @Test
    public void parseEntityToDTOTest() {
        BookDTO output = parseObject(inputObject.mockEntity(), BookDTO.class);
        assertEquals(Long.valueOf(0L), output.getId());

        assertEquals("Book Title0",  output.getTitle());
        assertEquals("Book Author0",  output.getAuthor());
        assertNotNull(output.getPublicationDate());
        assertEquals(LocalDate.now(), output.getPublicationDate());
        assertEquals("Book Genre0", output.getGenre());
        assertEquals(0D, output.getPrice());
    }

    @Test
    public void parseEntityListToDTOListTest() {
        List<BookDTO> outputList = parseListObjects(inputObject.mockEntityList(), BookDTO.class);
        BookDTO outputZero = outputList.getFirst();

        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Book Title0",  outputZero.getTitle());
        assertEquals("Book Author0",  outputZero.getAuthor());
        assertNotNull(outputZero.getPublicationDate());
        assertEquals(LocalDate.now(), outputZero.getPublicationDate());
        assertEquals("Book Genre0", outputZero.getGenre());
        assertEquals(0D, outputZero.getPrice());

        BookDTO outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("Book Title7",  outputSeven.getTitle());
        assertEquals("Book Author7",  outputSeven.getAuthor());
        assertNotNull(outputSeven.getPublicationDate());
        assertEquals(LocalDate.now(), outputSeven.getPublicationDate());
        assertEquals("Book Genre7", outputSeven.getGenre());
        assertEquals(7D, outputSeven.getPrice());

        BookDTO outputTwelve = outputList.get(12);

        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Book Title12",  outputTwelve.getTitle());
        assertEquals("Book Author12",  outputTwelve.getAuthor());
        assertNotNull(outputTwelve.getPublicationDate());
        assertEquals(LocalDate.now(), outputTwelve.getPublicationDate());
        assertEquals("Book Genre12", outputTwelve.getGenre());
        assertEquals(12D, outputTwelve.getPrice());
    }

    @Test
    public void parseDTOToEntityTest() {
        Book output = parseObject(inputObject.mockDTO(), Book.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Book Title0",  output.getTitle());
        assertEquals("Book Author0",  output.getAuthor());
        assertNotNull(output.getPublicationDate());
        assertEquals(LocalDate.now(), output.getPublicationDate());
        assertEquals("Book Genre0", output.getGenre());
        assertEquals(0D, output.getPrice());
    }

    @Test
    public void parserDTOListToEntityListTest() {
        List<Book> outputList = parseListObjects(inputObject.mockDTOList(), Book.class);
        Book outputZero = outputList.getFirst();

        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Book Title0",  outputZero.getTitle());
        assertEquals("Book Author0",  outputZero.getAuthor());
        assertNotNull(outputZero.getPublicationDate());
        assertEquals(LocalDate.now(), outputZero.getPublicationDate());
        assertEquals("Book Genre0", outputZero.getGenre());
        assertEquals(0D, outputZero.getPrice());

        Book outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("Book Title7",  outputSeven.getTitle());
        assertEquals("Book Author7",  outputSeven.getAuthor());
        assertNotNull(outputSeven.getPublicationDate());
        assertEquals(LocalDate.now(), outputSeven.getPublicationDate());
        assertEquals("Book Genre7", outputSeven.getGenre());
        assertEquals(7D, outputSeven.getPrice());

        Book outputTwelve = outputList.get(12);

        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Book Title12",  outputTwelve.getTitle());
        assertEquals("Book Author12",  outputTwelve.getAuthor());
        assertNotNull(outputTwelve.getPublicationDate());
        assertEquals(LocalDate.now(), outputTwelve.getPublicationDate());
        assertEquals("Book Genre12", outputTwelve.getGenre());
        assertEquals(12D, outputTwelve.getPrice());
    }
}