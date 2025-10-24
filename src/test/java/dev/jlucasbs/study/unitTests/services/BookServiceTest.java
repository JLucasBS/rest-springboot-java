package dev.jlucasbs.study.unitTests.services;

import dev.jlucasbs.study.data.dto.BookDTO;
import dev.jlucasbs.study.exception.RequiredObjectIsNullException;
import dev.jlucasbs.study.model.Book;
import dev.jlucasbs.study.repository.BookRepository;
import dev.jlucasbs.study.services.BookService;
import dev.jlucasbs.study.unitTests.mapper.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    MockBook input;

    @InjectMocks
    private BookService service;

    @Mock
    private BookRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockBook();

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByID() {
        Book book = input.mockEntity(1);
        book.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(book));

        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(book.getId(), result.getId());

        assertEquals("Book Title1",  result.getTitle());
        assertEquals("Book Author1",  result.getAuthor());
        assertNotNull(result.getPublicationDate());
        assertEquals(LocalDate.now(), result.getPublicationDate());
        assertEquals("Book Genre1", result.getGenre());
        assertEquals(1D, result.getPrice());
    }

    @Test
    void create() {
        Book book = input.mockEntity(1);
        book.setId(1L);

        BookDTO dto = input.mockDTO(1);

        when(repository.save(book)).thenReturn(book);

        var result = service.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(book.getId(), result.getId());

        assertEquals("Book Title1",  result.getTitle());
        assertEquals("Book Author1",  result.getAuthor());
        assertNotNull(result.getPublicationDate());
        assertEquals(LocalDate.now(), result.getPublicationDate());
        assertEquals("Book Genre1", result.getGenre());
        assertEquals(1D, result.getPrice());
    }

    @Test
    void testCreateWithNullBook() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> service.create(null));

        String expectedMessage = "Object must not be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void update() {
        Book book = input.mockEntity(1);
        book.setId(1L);

        BookDTO dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(book));
        when(repository.save(book)).thenReturn(book);

        var result = service.update(dto.getId(), dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(book.getId(), result.getId());

        assertEquals("Book Title1",  result.getTitle());
        assertEquals("Book Author1",  result.getAuthor());
        assertNotNull(result.getPublicationDate());
        assertEquals(LocalDate.now(), result.getPublicationDate());
        assertEquals("Book Genre1", result.getGenre());
        assertEquals(1D, result.getPrice());
    }

    @Test
    void testUpdateWithNullBook() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> service.update(1L, null));

        String expectedMessage = "Object must not be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void deleteByID() {
        Book book = input.mockEntity(1);
        book.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(book));

        service.delete(1L);
        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(Book.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findAll() {
        List<Book> list = input.mockEntityList();

        when(repository.findAll()).thenReturn(list);

        List<BookDTO> books = service.findAll();

        assertNotNull(books);
        assertEquals(14, books.size());

        var bookOne = books.get(1);

        assertNotNull(bookOne);
        assertNotNull(bookOne.getId());

        assertEquals("Book Title1",  bookOne.getTitle());
        assertEquals("Book Author1",  bookOne.getAuthor());
        assertNotNull(bookOne.getPublicationDate());
        assertEquals(LocalDate.now(), bookOne.getPublicationDate());
        assertEquals("Book Genre1", bookOne.getGenre());
        assertEquals(1D, bookOne.getPrice());

        var bookFour = books.get(4);

        assertNotNull(bookFour);
        assertNotNull(bookFour.getId());

        assertEquals("Book Title4",  bookFour.getTitle());
        assertEquals("Book Author4",  bookFour.getAuthor());
        assertNotNull(bookFour.getPublicationDate());
        assertEquals(LocalDate.now(), bookFour.getPublicationDate());
        assertEquals("Book Genre4", bookFour.getGenre());
        assertEquals(4D, bookFour.getPrice());

        var bookSeven = books.get(7);

        assertNotNull(bookSeven);
        assertNotNull(bookSeven.getId());

        assertEquals("Book Title7",  bookSeven.getTitle());
        assertEquals("Book Author7",  bookSeven.getAuthor());
        assertNotNull(bookSeven.getPublicationDate());
        assertEquals(LocalDate.now(), bookSeven.getPublicationDate());
        assertEquals("Book Genre7", bookSeven.getGenre());
        assertEquals(7D, bookSeven.getPrice());
    }
}