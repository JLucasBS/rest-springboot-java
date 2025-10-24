package dev.jlucasbs.study.services;

import dev.jlucasbs.study.data.dto.BookDTO;
import dev.jlucasbs.study.exception.RequiredObjectIsNullException;
import dev.jlucasbs.study.exception.ResourceNotFoundException;
import dev.jlucasbs.study.model.Book;
import dev.jlucasbs.study.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static dev.jlucasbs.study.mapper.ObjectMapper.parseListObjects;
import static dev.jlucasbs.study.mapper.ObjectMapper.parseObject;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookDTO> findAll() {
        var books = bookRepository.findAll();

        return parseListObjects(books, BookDTO.class);
    }

    public BookDTO findById(Long id) {
        var book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        return parseObject(book, BookDTO.class);
    }

    public BookDTO create(BookDTO bookDTO) {
        if (bookDTO == null) throw new RequiredObjectIsNullException();

        var entity = parseObject(bookDTO, Book.class);

        return parseObject(bookRepository.save(entity), BookDTO.class);
    }

    public BookDTO update(@PathVariable("id") Long id, BookDTO bookDTO) {
        if (bookDTO == null) throw new RequiredObjectIsNullException();

        Book entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setAuthor(bookDTO.getAuthor());
        entity.setTitle(bookDTO.getTitle());
        entity.setPrice(bookDTO.getPrice());
        entity.setPublicationDate(bookDTO.getPublicationDate());
        entity.setGenre(bookDTO.getGenre());

        return parseObject(bookRepository.save(entity),  BookDTO.class);
    }

    public void delete(Long id) {
        Book entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        bookRepository.delete(entity);
    }
}
