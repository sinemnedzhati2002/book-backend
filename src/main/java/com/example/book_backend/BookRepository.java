package com.example.book_backend;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Für einfache Use-Cases reicht das erstmal.
    // Filtern machen wir im Service.
}
