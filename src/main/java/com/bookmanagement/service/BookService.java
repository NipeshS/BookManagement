package com.bookmanagement.service;

import com.bookmanagement.dto.BookRequestDto;
import com.bookmanagement.dto.BookResponseDto;
import com.bookmanagement.entity.Book;
import com.bookmanagement.repository.BookRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Create Book
    public BookResponseDto createBook(BookRequestDto requestDto) {

        Book book = new Book();

        book.setTitle(requestDto.getTitle());
        book.setAuthor(requestDto.getAuthor());
        book.setPrice(requestDto.getPrice());
        book.setCategory(requestDto.getCategory());

        Book savedBook = bookRepository.save(book);

        return convertToResponseDto(savedBook);
    }

    // Get All Books
    public List<BookResponseDto> getAllBooks() {

        return bookRepository.findAll()
                .stream()
                .map(this::convertToResponseDto)
                .toList();
    }

    // Get Book By ID
    public BookResponseDto getBookById(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Book not found with ID: " + id));

        return convertToResponseDto(book);
    }


    public void deleteBook(Long id) {

        if (!bookRepository.existsById(id)) {
            throw new RuntimeException(
                    "Book not found with ID: " + id);
        }

        bookRepository.deleteById(id);
    }

    // Convert Entity to Response DTO
    private BookResponseDto convertToResponseDto(Book book) {

        return new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPrice(),
                book.getCategory()
        );
    }
}