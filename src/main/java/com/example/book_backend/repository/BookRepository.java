package com.example.book_backend.repository;

import com.example.book_backend.entity.Book;
import com.example.book_backend.entity.ReadingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByStatus(ReadingStatus status);

}
