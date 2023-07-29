package by.dubrovsky.librarybackend.repository;

import by.dubrovsky.librarybackend.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// репозиторий для модели книги
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByOrderById();
}
