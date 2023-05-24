package by.dubrovsky.librarybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// репозиторий для модели пользователя

@Repository
public interface UserRepository extends JpaRepository<UserRepository, Long> {

}
