package com.example.leesplezier.controllers;

import com.example.leesplezier.dtos.BookDto;
import com.example.leesplezier.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> bookDtos = bookService.getAllBooks();

        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping("/{bookISBN}")
    public ResponseEntity<BookDto> getBookByISBN(@PathVariable("bookISBN") Long ISBN) {
        BookDto bookDto = bookService.getBookByISBN(ISBN);
        return ResponseEntity.ok().body(bookDto);
    }


    @GetMapping("/titles/{title}")
    public ResponseEntity<Iterable<BookDto>> getBooksByTitle(@PathVariable String title) {
        List<BookDto> bDtos = bookService.getBookByTitle(title);
        return ResponseEntity.ok(bDtos);
    }

    @GetMapping("/subjects/{subject}")
    public ResponseEntity<Iterable<BookDto>> getBooksbySubjects(@PathVariable String subject) {
        List<BookDto> bDtos = bookService.getBookBySubject(subject);
        return ResponseEntity.ok(bDtos);
    }

    @GetMapping("/target_age/{age}")
    public ResponseEntity<Iterable<BookDto>> getBooksByTargetAge(@PathVariable int age) {
        List<BookDto> bDtos = bookService.getBooksByAge(age);
        return ResponseEntity.ok(bDtos);
    }

    @GetMapping("/authors/{name}")
    public ResponseEntity<Iterable<BookDto>> getBooksByAuthor(@PathVariable String name) {
        List<BookDto> bDtos = bookService.getBookByAuthor(name);
        return ResponseEntity.ok(bDtos);
    }


    @PostMapping
    public ResponseEntity<Object> addBook(@Valid @RequestBody BookDto bookDto, BindingResult br) {
        if (br.hasFieldErrors()) {
            var sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField());
                sb.append(" : ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        } else {

            bookService.addBook(bookDto);
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/" + bookDto.getISBN()).toUriString());

            return ResponseEntity.created(uri).body(bookDto);
        }
    }

    @PutMapping("/{ISBN}")
    public ResponseEntity<Object> updateBook(@PathVariable("ISBN") Long ISBN, @Valid @RequestBody BookDto bookDto) {
        bookService.updateBook(ISBN, bookDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{ISBN}")
    public ResponseEntity<Object> deleteBook(@PathVariable("ISBN") Long ISBN) {
        bookService.deleteBook(ISBN);
        return ResponseEntity.noContent().build();
    }
}
