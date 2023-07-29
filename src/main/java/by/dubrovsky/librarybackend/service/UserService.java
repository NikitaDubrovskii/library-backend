package by.dubrovsky.librarybackend.service;

import by.dubrovsky.librarybackend.dto.UserDTO;
import by.dubrovsky.librarybackend.entity.User;
import by.dubrovsky.librarybackend.facade.UserFacade;
import by.dubrovsky.librarybackend.repository.BookRepository;
import by.dubrovsky.librarybackend.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// сервис для работы с моделью пользователя
@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final UserFacade userFacade;

    public UserService(UserRepository userRepository, BookRepository bookRepository,
                       UserFacade userFacade) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.userFacade = userFacade;
    }

    @Transactional
    public UserDTO add(User user) {
        user.setRegistrationDate(LocalDateTime.now());
        User save = userRepository.save(user);
        return userFacade.userToDTO(save);
    }

    public UserDTO getById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            return userFacade.userToDTO(user);
        } else {
            return null;
        }
    }

    public List<UserDTO> getAll() {
        List<User> all = userRepository.findAllByOrderById();
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : all) {
            UserDTO userDTO = userFacade.userToDTO(user);
            usersDTO.add(userDTO);
        }
        return usersDTO;
    }

    /*TODO доделать обновление с ролью админа
       надо чтобы была авторизация и тогда с проверкой, что этот авторизированный пользователь
       является админов, можно менять роль кому угодно
    */
    @Transactional
    public UserDTO update(User userFromDb, User userToUpdate) {
        if (userFromDb.getAdmin() != null && userToUpdate.getAdmin() != null) {
            BeanUtils.copyProperties(userToUpdate, userFromDb, "id", "registrationDate");
        } else {
            BeanUtils.copyProperties(userToUpdate, userFromDb, "id", "registrationDate", "admin");
        }
        User save = userRepository.save(userFromDb);
        return userFacade.userToDTO(save);
    }

    @Transactional
    public UserDTO deleteById(Long id) {
        UserDTO userDTO = getById(id);
        userRepository.deleteById(id);
        return userDTO;
    }
}
