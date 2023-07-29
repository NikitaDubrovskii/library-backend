package by.dubrovsky.librarybackend.repository;

import by.dubrovsky.librarybackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// репозиторий для модели пользователя

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByOrderById();
}
