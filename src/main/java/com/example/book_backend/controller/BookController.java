/**package com.example.book_backend.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // später durch deine Frontend-URL ersetzen
public class BookController {

    @GetMapping("/books")
    public List<String> getBooks() {
        return List.of(
                "Harry Potter",
                "Der Herr der Ringe",
                "Der Hobbit"
        );
    }
}*/


package com.example.book_backend.controller;

import com.example.book_backend.Book;
import com.example.book_backend.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")  // später durch deine Render-Frontend-URL ersetzen
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    // GET /api/books  (optional filter: ?title=Harry&author=Rowling&genre=Fantasy)
    @GetMapping
    public List<Book> getAllBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String genre
    ) {
        return service.getAll(title, author, genre);
    }

    // GET /api/books/{id}
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return service.getById(id);
    }

    // POST /api/books
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return service.create(book);
    }

    // PUT /api/books/{id}
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return service.update(id, book);
    }

    // DELETE /api/books/{id}
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        service.delete(id);
    }
}
