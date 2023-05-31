package by.dubrovsky.librarybackend.controller;

import by.dubrovsky.librarybackend.entity.Book;
import by.dubrovsky.librarybackend.entity.User;
import by.dubrovsky.librarybackend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// конроллер для работы с моделью пользователя
@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public User add(@RequestBody User user) {
        return userService.add(user);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @GetMapping()
    public List<User> getAll() {
        return userService.getAll();
    }

    @PutMapping("/update/{id}")
    public User update(@PathVariable("id") User userFromDb, @RequestBody User userToUpdate) {
        return userService.update(userFromDb, userToUpdate);
    }

    @DeleteMapping("/delete/{id}")
    public User delete(@PathVariable("id") Long id) {
        return userService.deleteById(id);
    }
}
