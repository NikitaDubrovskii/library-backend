package by.dubrovsky.librarybackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

// модель пользователя

@Entity
@Table(name = "user")
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

    @Column(name = "role")
    private String role;

    public User(String firstName, String secondName, Integer age, String email, LocalDateTime registrationDate, String role) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.email = email;
        this.registrationDate = registrationDate;
        this.role = role;
    }
}


