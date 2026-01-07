package com.example.book_backend.service;

import com.example.book_backend.entity.Book;
import com.example.book_backend.entity.ReadingStatus;
import com.example.book_backend.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Service
public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    // Use Case 1: Alle Bücher + Filter
    public List<Book> getAll(String title, String author, String genre) {
        List<Book> all = repo.findAll();
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
    // Use Case 2: Buch per ID
    public Book getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found: " + id));
    }
    // Use Case 3: To-Read Liste
    public List<Book> getToRead() {
        return repo.findByStatus(ReadingStatus.TO_READ);
    }

    // Use Case 4: Fertig gelesen Liste
    public List<Book> getFinished() {
        return repo.findByStatus(ReadingStatus.FINISHED);
    }

    // Use Case 5: Buch anlegen
    public Book create(Book book) {
        normalizeDates(book);
        return repo.save(book);
    }

    // Use Case 6: Buch aktualisieren
    public Book update(Long id, Book updated) {
        Book existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found: " + id));

        existing.setTitle(updated.getTitle());
        existing.setAuthor(updated.getAuthor());
        existing.setGenre(updated.getGenre());
        existing.setRating(updated.getRating());
        existing.setPlannedOn(updated.getPlannedOn());
        //existing.setFinishedOn(updated.getFinishedOn());
        existing.setStatus(updated.getStatus());

        if (updated.getStatus() == ReadingStatus.FINISHED) {
            if (updated.getFinishedOn() != null) {
                // Benutzer hat bewusst ein Datum gesetzt → übernehmen
                existing.setFinishedOn(updated.getFinishedOn());
            } else if (existing.getFinishedOn() == null) {
                // Kein Datum vorhanden → heute setzen
                existing.setFinishedOn(LocalDate.now());
            }
        } else {
            // Nicht FINISHED → finishedOn muss null sein
            existing.setFinishedOn(null);
        }

       // normalizeDates(existing);
        return repo.save(existing);
    }

    // Use Case 7: Status ändern
    public Book updateStatus(Long id, ReadingStatus status) {
        Book book = getById(id);
        book.setStatus(status);
        normalizeDates(book);
        return repo.save(book);
    }

    // Use Case 8: Löschen
    public void delete(Long id) {
        repo.deleteById(id);
    }
    //  Datumsfelder konsistent halten
    private void normalizeDates(Book b) {
        if (b.getStatus() == null) {
            b.setStatus(ReadingStatus.TO_READ);
        }

        // - FINISHED: finishedOn muss gesetzt sein (falls null -> heute
        // - sonst: finishedOn MUSS null sein
        if (b.getStatus() == ReadingStatus.FINISHED) {
            if (b.getFinishedOn() == null) {
                b.setFinishedOn(LocalDate.now());
            }
        } else {
            // Wenn nicht finished, dann finishedOn weg (sonst inkonsistent)
            b.setFinishedOn(null);
        }

    }
}
