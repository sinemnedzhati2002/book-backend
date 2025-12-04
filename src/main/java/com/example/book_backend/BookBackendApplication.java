package com.example.book_backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class BookBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(BookRepository repo) {
        return args -> {
            if (repo.count() == 0) {

                // ---- Catharina Maura ----
                repo.save(new Book(
                        "The Wrong Bride",
                        "Catharina Maura",
                        "Romance",
                        5,
                        LocalDate.of(2022, 6, 1)
                ));

                repo.save(new Book(
                        "The Unwanted Marriage",
                        "Catharina Maura",
                        "Romance",
                        4,
                        LocalDate.of(2023, 3, 15)
                ));

                repo.save(new Book(
                        "The Temporary Wife",
                        "Catharina Maura",
                        "Romance",
                        4,
                        LocalDate.of(2021, 11, 10)
                ));

                // ---- Ana Huang ----
                repo.save(new Book(
                        "Twisted Love",
                        "Ana Huang",
                        "Romance",
                        5,
                        LocalDate.of(2021, 4, 10)
                ));

                repo.save(new Book(
                        "Twisted Games",
                        "Ana Huang",
                        "Romance",
                        4,
                        LocalDate.of(2021, 7, 15)
                ));

                repo.save(new Book(
                        "Twisted Hate",
                        "Ana Huang",
                        "Romance",
                        5,
                        LocalDate.of(2022, 5, 20)
                ));

                // ---- Klassiker ----
                repo.save(new Book(
                        "Stolz und Vorurteil",
                        "Jane Austen",
                        "Klassiker",
                        5,
                        LocalDate.of(2020, 1, 5)
                ));

                repo.save(new Book(
                        "Der Prozess",
                        "Franz Kafka",
                        "Klassiker",
                        4,
                        LocalDate.of(2019, 10, 12)
                ));

                repo.save(new Book(
                        "1984",
                        "George Orwell",
                        "Klassiker",
                        4,
                        LocalDate.of(2018, 9, 1)
                ));

                repo.save(new Book(
                        "Der Herr der Ringe",
                        "J.R.R. Tolkien",
                        "Fantasy / Klassiker",
                        3,
                        LocalDate.of(2017, 12, 24)
                ));
            }
        };
    }
}
