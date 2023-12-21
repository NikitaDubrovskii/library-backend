package by.dubrovsky.librarybackend.service;

import by.dubrovsky.librarybackend.LibraryBackendApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MainService {

    private final ConfigurableApplicationContext context;

    public MainService() {
        this.context = LibraryBackendApplication.context;
    }

    public void stopApplication() {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (context != null) {
                SpringApplication.exit(context);
            }
            System.exit(0);
        });
        thread.start();
    }

}
