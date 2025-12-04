package com.example.book_backend;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public List<Book> getAll(String title, String author, String genre) {
        List<Book> all = repo.findAll();

        // Einfaches Filtern im Speicher – reicht für dein Projekt völlig
        return all.stream()
                .filter(b -> title == null || b.getTitle() != null &&
                        b.getTitle().toLowerCase(Locale.ROOT)
                                .contains(title.toLowerCase(Locale.ROOT)))
                .filter(b -> author == null || b.getAuthor() != null &&
                        b.getAuthor().toLowerCase(Locale.ROOT)
                                .contains(author.toLowerCase(Locale.ROOT)))
                .filter(b -> genre == null || b.getGenre() != null &&
                        b.getGenre().toLowerCase(Locale.ROOT)
                                .contains(genre.toLowerCase(Locale.ROOT)))
                .toList();
    }

    public Book getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found: " + id));
    }

    public Book create(Book book) {
        // id bleibt null, wird von JPA vergeben
        return repo.save(book);
    }

    public Book update(Long id, Book updated) {
        return repo.findById(id)
                .map(existing -> {
                    existing.setTitle(updated.getTitle());
                    existing.setAuthor(updated.getAuthor());
                    existing.setGenre(updated.getGenre());
                    existing.setRating(updated.getRating());
                    existing.setFinishedOn(updated.getFinishedOn());
                    return repo.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Book not found: " + id));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
