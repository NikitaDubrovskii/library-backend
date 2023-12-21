package by.dubrovsky.librarybackend;

import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LibraryBackendApplication {

    public static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(LibraryBackendApplication.class, args);
    }

    /*public void stopApplication() {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (context != null) {
                SpringApplication.exit(context);
            }
            System.exit(0);
        });
        thread.start();
    }*/

}
