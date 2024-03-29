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
import java.util.Date;
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
            /*long diffInMilles = Math.abs(book.getTakenAt().getTime() - new Date().getTime()); // 30 дней = 2592000000 миллисекунд
            if (diffInMilles > 2592000000L) {
                book.setExpired(true);
            }*/
            return bookFacade.bookToDTO(book);
        } else {
            return null;
        }
    }

    @Transactional
    public List<BookDTO> getAll() {
        List<Book> all = bookRepository.findAllByOrderById();
        all.forEach(book -> {
            if (book.getTaken() != null) {
                if (book.getTaken() && !book.getExpired()) {
                    long diffInMilles = Math.abs(book.getTakenAt().getTime() - new Date().getTime()); // 30 дней = 2592000000 миллисекунд
                    if (diffInMilles > 2592000000L) {
                        bookRepository.setExpired(book.getId(), true);
                    }
                }
            }
        });
        List<BookDTO> booksDTO = new ArrayList<>();
        for (Book book : all) {
            BookDTO bookDTO = bookFacade.bookToDTO(book);
            booksDTO.add(bookDTO);
        }
        return booksDTO;
    }

    @Transactional
    public BookDTO update(Book bookFromDb, Book bookToUpdate) {
        BeanUtils.copyProperties(bookToUpdate, bookFromDb, "id", "user_id", "taken", "taken_at");
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
        book.setTaken(true);
        book.setTakenAt(new Date());

        Book bookToUpdate = new Book();
        BeanUtils.copyProperties(book, bookToUpdate,
                "id", "title", "author", "page", "publicationDate"); // игнорируем параметры, которые не выставляются в этом методе
        Book save = bookRepository.save(book);
        return bookFacade.bookToDTO(save);
    }

    @Transactional
    public BookDTO takeBook(Book book) {
        book.setUserId(null);
        book.setTaken(false);
        book.setTakenAt(null);
        book.setExpired(false);

        Book bookToUpdate = new Book();
        BeanUtils.copyProperties(book, bookToUpdate,
                "id", "title", "author", "page", "publicationDate"); // игнорируем параметры, которые не выставляются в этом методе
        Book save = bookRepository.save(book);
        return bookFacade.bookToDTO(save);
    }
}
