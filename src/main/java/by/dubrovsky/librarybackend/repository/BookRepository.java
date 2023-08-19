package by.dubrovsky.librarybackend.repository;

import by.dubrovsky.librarybackend.dto.BookDTO;
import by.dubrovsky.librarybackend.dto.UserDTO;
import by.dubrovsky.librarybackend.entity.Book;
import by.dubrovsky.librarybackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// репозиторий для модели книги
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByOrderById();
    List<Book> findBooksByUserId(User user);

    @Modifying
    @Query("UPDATE Book b SET b.expired = :newExpired WHERE b.id = :bookId")
    void setExpired(@Param("bookId") Long bookId, @Param("newExpired") Boolean expired);
}
