package by.dubrovsky.librarybackend.facade;

import by.dubrovsky.librarybackend.dto.BookDTO;
import by.dubrovsky.librarybackend.dto.UserDTO;
import by.dubrovsky.librarybackend.entity.Book;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

// соотносит модель с клиента (dto) с моделью на сервере
@Component
public class BookFacade {
    private final UserFacade userFacade;

    public BookFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public BookDTO bookToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        BeanUtils.copyProperties(book, bookDTO);
        return bookDTO;
    }
}
