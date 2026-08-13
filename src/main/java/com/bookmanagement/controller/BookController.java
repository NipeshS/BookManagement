package com.bookmanagement.controller;

import com.bookmanagement.dto.BookRequestDto;
import com.bookmanagement.dto.BookResponseDto;
import com.bookmanagement.service.BookService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // POST /books
    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(
            @Valid @RequestBody BookRequestDto requestDto) {

        BookResponseDto response = bookService.createBook(requestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // GET /books
    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {

        List<BookResponseDto> books = bookService.getAllBooks();

        return ResponseEntity.ok(books);
    }

    // GET /books/{id}
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(
            @PathVariable Long id) {

        BookResponseDto book = bookService.getBookById(id);

        return ResponseEntity.ok(book);
    }

    // DELETE /books/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(
            @PathVariable Long id) {

        bookService.deleteBook(id);

        return ResponseEntity.ok("Book deleted successfully");
    }
}