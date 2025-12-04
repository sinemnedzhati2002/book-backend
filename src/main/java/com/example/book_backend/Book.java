package com.example.book_backend;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Integer rating;      // 1-5 Sterne
    private LocalDate finishedOn;

    public Book() {}

    public Book(String title, String author, String genre, Integer rating, LocalDate finishedOn) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.rating = rating;
        this.finishedOn = finishedOn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                ", finishedOn=" + finishedOn +
                '}';
    }
}
