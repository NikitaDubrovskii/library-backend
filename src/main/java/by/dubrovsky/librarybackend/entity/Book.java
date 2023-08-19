package by.dubrovsky.librarybackend.entity;

import by.dubrovsky.librarybackend.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

// модель книги

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id", "title", "author"})
@EqualsAndHashCode(of = {"id"})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "page")
    private Integer page;

    @Column(name = "publication_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
    private LocalDate publicationDate;

    @Column(name = "taken")
    private Boolean taken;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnoreProperties("bookList")
    private User userId;

    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;

    @Column(name = "expired")
    private Boolean expired;

    public Book(String title, String author, Integer page,
                LocalDate publicationDate, Boolean taken,
                User userId, Date takenAt, Boolean expired) {
        this.title = title;
        this.author = author;
        this.page = page;
        this.publicationDate = publicationDate;
        this.taken = taken;
        this.userId = userId;
        this.takenAt = takenAt;
        this.expired = expired;
    }
}
