package by.dubrovsky.librarybackend.repository;

import by.dubrovsky.librarybackend.dto.BookDTO;
import by.dubrovsky.librarybackend.dto.UserDTO;
import by.dubrovsky.librarybackend.entity.Book;
import by.dubrovsky.librarybackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// репозиторий для модели книги
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByOrderById();
    List<Book> findBooksByUserId(User user);
}
