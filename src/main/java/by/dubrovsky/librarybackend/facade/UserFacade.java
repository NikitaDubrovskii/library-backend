package by.dubrovsky.librarybackend.facade;

import by.dubrovsky.librarybackend.dto.UserDTO;
import by.dubrovsky.librarybackend.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

// соотносит модель с клиента (dto) с моделью на сервере
@Component
public class UserFacade {
    public UserDTO userToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO, "age", "registrationDate", "admin");
        return userDTO;
    }
}
