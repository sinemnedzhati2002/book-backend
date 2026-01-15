
package com.example.book_backend.controller;

import com.example.book_backend.entity.Book;
import com.example.book_backend.entity.ReadingStatus;
import com.example.book_backend.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/books")
/**@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://book-frontend-2-qbx4.onrender.com"
})*/
@CrossOrigin(
        origins = {
                "http://localhost:5173",
                "https://book-frontend-2-qbx4.onrender.com"
        },
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.OPTIONS}
)

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

    @GetMapping("/toread")
    public List<Book> getToReadBooks() {
        return service.getToRead();
    }

    @GetMapping("/finished")
    public List<Book> getFinishedBooks() {
        return service.getFinished();
    }

    // POST /api/books
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book created = service.create(book);
        return ResponseEntity.created(URI.create("/api/books/" + created.getId())).body(created);
    }

    // PUT /api/books/{id}
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return service.update(id, book);
    }

    @GetMapping("/{id}/status")
    public Book changeStatus(@PathVariable Long id, @RequestBody StatusUpdateRequest req) {
        return service.updateStatus(id, req.status());
    }

    public record StatusUpdateRequest(ReadingStatus status) {}

    // DELETE /api/books/{id}
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        service.delete(id);
    }






}
