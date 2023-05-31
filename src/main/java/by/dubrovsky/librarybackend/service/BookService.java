package by.dubrovsky.librarybackend.service;

import by.dubrovsky.librarybackend.entity.Book;
import by.dubrovsky.librarybackend.entity.User;
import by.dubrovsky.librarybackend.repository.BookRepository;
import by.dubrovsky.librarybackend.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// сервис для работы с моделью книги
@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
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
    public Book update(Book bookFromDb, Book bookToUpdate) {
        BeanUtils.copyProperties(bookToUpdate, bookFromDb, "id");
        return bookRepository.save(bookFromDb);
    }

    @Transactional
    public Book deleteById(Long id) {
        Book book = getById(id);
        bookRepository.deleteById(id);
        return book;
    }

    @Transactional
    public Book giveBook(Book book, User user) {
        book.setUserId(user);
        Book bookToUpdate = new Book();
        BeanUtils.copyProperties(book, bookToUpdate,
                "id", "title", "author", "page", "publicationDate", "quantity");
        return bookRepository.save(book);
    }
}
