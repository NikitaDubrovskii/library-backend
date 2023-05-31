package by.dubrovsky.librarybackend.service;

import by.dubrovsky.librarybackend.entity.User;
import by.dubrovsky.librarybackend.repository.BookRepository;
import by.dubrovsky.librarybackend.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

// сервис для работы с моделью пользователя
@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public UserService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public User add(User user) {
        user.setRegistrationDate(LocalDateTime.now());
        return userRepository.save(user);
    }

    /*TODO нужно добавить dto слой для того чтобы мы могли
       отправлять фронту модель юзера с книгами,
       т.к. в ентити нельзя добавить просто поле с книгами
    */
    public User getById(Long id) {

        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();

            return userRepository.findById(id).get();
        } else {
            return null;
        }
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    /*TODO доделать обновление с ролью админа
       надо чтобы была авторизация и тогда с проверкой, что этот авторизированный пользователь
       является админов, можно менять роль кому угодно
    */
    @Transactional
    public User update(User userFromDb, User userToUpdate) {
        if (userFromDb.getAdmin() != null && userToUpdate.getAdmin() != null) {
            BeanUtils.copyProperties(userToUpdate, userFromDb, "id", "registrationDate");
        } else {
            BeanUtils.copyProperties(userToUpdate, userFromDb, "id", "registrationDate", "admin");
        }
        return userRepository.save(userFromDb);
    }

    @Transactional
    public User deleteById(Long id) {
        User user = getById(id);
        userRepository.deleteById(id);
        return user;
    }
}
