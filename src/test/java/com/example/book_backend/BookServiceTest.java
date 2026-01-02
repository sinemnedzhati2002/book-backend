package com.example.book_backend;

import com.example.book_backend.entity.Book;
import com.example.book_backend.entity.ReadingStatus;
import com.example.book_backend.repository.BookRepository;
import com.example.book_backend.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookServiceTest {

    @Autowired BookService service;
    @Autowired BookRepository repo;

    @Test
    void whenStatusFinished_andFinishedOnNull_thenFinishedOnIsSet() {
        Book b = new Book(
                "Rule Test",
                "Tester",
                "Genre",
                5,
                null,   // plannedOn
                null,   // finishedOn absichtlich null
                ReadingStatus.FINISHED
        );

        Book saved = service.create(b);

        assertThat(saved.getStatus()).isEqualTo(ReadingStatus.FINISHED);
        assertThat(saved.getFinishedOn()).isNotNull();
        // optional: sollte heute sein (kann man testen, ist aber nicht nötig)
        assertThat(saved.getFinishedOn()).isEqualTo(LocalDate.now());

        repo.deleteById(saved.getId());
    }
}
