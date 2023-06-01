package by.dubrovsky.librarybackend.controller;

import by.dubrovsky.librarybackend.dto.BookDTO;
import by.dubrovsky.librarybackend.entity.Book;
import by.dubrovsky.librarybackend.entity.User;
import by.dubrovsky.librarybackend.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// конроллер для работы с моделью книги
@RestController
@RequestMapping("book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public BookDTO add(@RequestBody Book book) {
        return bookService.add(book);
    }

    @GetMapping("/{id}")
    public BookDTO getById(@PathVariable("id") Long id) {
        return bookService.getById(id);
    }

    @GetMapping()
    public List<BookDTO> getAll() {
        return bookService.getAll();
    }

    @PutMapping("/update/{id}")
    public BookDTO update(@PathVariable("id") Book bookFromDb, @RequestBody Book bookToUpdate) {
        return bookService.update(bookFromDb, bookToUpdate);
    }

    @DeleteMapping("/delete/{id}")
    public BookDTO delete(@PathVariable("id") Long id) {
        return bookService.deleteById(id);
    }

    @PutMapping("/{bookId}/{userId}")
    public BookDTO giveBook(@PathVariable("bookId") Book book, @PathVariable("userId") User user) {
        return bookService.giveBook(book, user);
    }
}
