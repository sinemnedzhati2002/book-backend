package com.example.book_backend.entity;
import com.example.book_backend.entity.ReadingStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String genre;
    private Integer rating;
    private LocalDate plannedOn;   // geplant zu lesen
    private LocalDate finishedOn; // fertig gelesen


    @Enumerated(EnumType.STRING)
    private ReadingStatus status = ReadingStatus.TO_READ;

    public Book() {}

    public Book(String title, String author, String genre,
                Integer rating, LocalDate plannedOn,
                LocalDate finishedOn, ReadingStatus status) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.rating = rating;
        this.plannedOn = plannedOn;
        this.finishedOn = finishedOn;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", status=" + status +
                ", plannedOn=" + plannedOn +
                ", finishedOn=" + finishedOn +
                '}';
    }
}
