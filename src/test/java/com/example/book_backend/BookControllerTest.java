package com.example.book_backend;


import com.example.book_backend.entity.Book;
import com.example.book_backend.entity.ReadingStatus;
import com.example.book_backend.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired MockMvc mvc;
    @Autowired BookRepository repo;

    @BeforeEach
    void setup() {
        repo.deleteAll();
        repo.save(new Book(
                "Test Book",
                "Test Author",
                "Test Genre",
                4,
                LocalDate.of(2025, 1, 10),
                null,
                ReadingStatus.TO_READ
        ));
    }

    @Test
    void getAllBooks_returns200_andJsonArray() throws Exception {
        mvc.perform(get("/api/books")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                // mindestens 1 Buch vorhanden
                .andExpect(jsonPath("$[0].title").value("Test Book"))
                .andExpect(jsonPath("$[0].author").value("Test Author"));
    }
}
