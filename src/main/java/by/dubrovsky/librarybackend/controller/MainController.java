package by.dubrovsky.librarybackend.controller;

import by.dubrovsky.librarybackend.service.MainService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//контроллер для полного выключения приложения (нужен для работы desktop приложения)
@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class MainController {

    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @DeleteMapping("/shutdown")
    public void shutdown() {
        mainService.stopApplication();
    }
}
