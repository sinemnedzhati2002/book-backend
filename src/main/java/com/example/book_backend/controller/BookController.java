package com.example.bookbackend.controller;

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
}
