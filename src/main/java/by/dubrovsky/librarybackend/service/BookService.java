package by.dubrovsky.librarybackend.service;

import by.dubrovsky.librarybackend.dto.BookDTO;
import by.dubrovsky.librarybackend.entity.Book;
import by.dubrovsky.librarybackend.entity.User;
import by.dubrovsky.librarybackend.facade.BookFacade;
import by.dubrovsky.librarybackend.repository.BookRepository;
import by.dubrovsky.librarybackend.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

// сервис для работы с моделью книги
@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BookFacade bookFacade;

    public BookService(BookRepository bookRepository, UserRepository userRepository,
                       BookFacade bookFacade) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.bookFacade = bookFacade;
    }

    @Transactional
    public BookDTO add(Book book) {
        Book save = bookRepository.save(book);
        return bookFacade.bookToDTO(save);
    }

    public BookDTO getById(Long id) {
        if (bookRepository.findById(id).isPresent()) {
            Book book = bookRepository.findById(id).get();
            return bookFacade.bookToDTO(book);
        } else {
            return null;
        }
    }

    public List<BookDTO> getAll() {
        List<Book> all = bookRepository.findAll();
        List<BookDTO> booksDTO = new ArrayList<>();
        for (Book book : all) {
            BookDTO bookDTO = bookFacade.bookToDTO(book);
            booksDTO.add(bookDTO);
        }
        return booksDTO;
    }

    @Transactional
    public BookDTO update(Book bookFromDb, Book bookToUpdate) {
        BeanUtils.copyProperties(bookToUpdate, bookFromDb, "id");
        Book book = bookRepository.save(bookFromDb);
        return bookFacade.bookToDTO(book);
    }

    @Transactional
    public BookDTO deleteById(Long id) {
        BookDTO bookDTO = getById(id);
        bookRepository.deleteById(id);
        return bookDTO;
    }

    @Transactional
    public BookDTO giveBook(Book book, User user) {
        book.setUserId(user);
        Book bookToUpdate = new Book();
        BeanUtils.copyProperties(book, bookToUpdate,
                "id", "title", "author", "page", "publicationDate", "quantity");
        Book save = bookRepository.save(book);
        return bookFacade.bookToDTO(save);
    }
}
