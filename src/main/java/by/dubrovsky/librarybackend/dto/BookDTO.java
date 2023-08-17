package by.dubrovsky.librarybackend.dto;

import by.dubrovsky.librarybackend.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

// dto для разделения модели на сервере и модели на клиенте
// (не все данные нужны клиенту, которые есть на сервере)
@Data
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private Integer page;
    private Boolean taken;
    private User userId;
}
