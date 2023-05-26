package by.dubrovsky.librarybackend.service;

import by.dubrovsky.librarybackend.entity.Book;
import by.dubrovsky.librarybackend.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// сервис для работы с моделью книги
@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public Book add(Book book) {
        return bookRepository.save(book);
    }

    public Book getById(Long id) {
        if (bookRepository.findById(id).isPresent()) {
            return bookRepository.findById(id).get();
        } else {
            return null;
        }
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Transactional
    public Book update(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public void delete(Book book) {
        bookRepository.delete(book);
    }
}
