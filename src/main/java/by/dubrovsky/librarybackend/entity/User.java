package by.dubrovsky.librarybackend.entity;

import by.dubrovsky.librarybackend.dto.BookDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

// модель пользователя

@Entity
@Table(name = "usr")
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id", "first_name", "second_name"})
@EqualsAndHashCode(of = {"id"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email")
    private String email;

    @Column(name = "registration_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registrationDate;

    @Column(name = "admin")
    private Boolean admin;

    @OneToMany(mappedBy = "userId", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("userId")
    private List<Book> bookList;

    public User(String firstName, String secondName, Integer age,
                String email, LocalDateTime registrationDate, Boolean admin) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.email = email;
        this.registrationDate = registrationDate;
        this.admin = admin;
    }
}


