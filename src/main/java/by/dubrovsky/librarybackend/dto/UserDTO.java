package by.dubrovsky.librarybackend.dto;

import by.dubrovsky.librarybackend.entity.Book;
import lombok.Data;

import java.util.List;

// dto для разделения модели на сервере и модели на клиенте
// (не все данные нужны клиенту, которые есть на сервере)
@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String secondName;
    private String email;
    private List<Book> bookList;
}
