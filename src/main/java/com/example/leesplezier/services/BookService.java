package com.example.leesplezier.services;

import com.example.leesplezier.dtos.BookDto;

import com.example.leesplezier.exceptions.RecordNotFoundException;
import com.example.leesplezier.models.Book;
import com.example.leesplezier.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.*;


@Service
public class BookService {

    //dependency injection
    private BookRepository bRepos;

    public BookService(BookRepository bRepos) {
        this.bRepos = bRepos;

    }

    public List<BookDto> getAllBooks() {
        Iterable<Book> bList = bRepos.findAll();

        List<BookDto> bDtos = new ArrayList<>();
        for (Book b : bList) {
            bDtos.add(transferToDto(b));
        }
        return bDtos;
    }

    public BookDto getBookByISBN(Long ISBN) {
        Optional<Book> book = bRepos.findById(ISBN);
        if (book.isPresent()) {
            BookDto bDto = transferToDto(book.get());
            return bDto;
        } else {
            throw new RecordNotFoundException("No book has been found with that ISBN, please try again with a different number.");
        }
    }

    public List<BookDto> getBookByTitle(String title) {
        List<Book> books = bRepos.findByTitleContainsIgnoreCase(title);
        List<BookDto> bySubject = new ArrayList<>();

        if (books.isEmpty()) {
            throw new RecordNotFoundException("No books match the given title, try again with a different one.");

        } else {
            for (Book b : books) {
                bySubject.add(transferToDto(b));
            }
            return bySubject;
        }
    }

    public List<BookDto> getBookBySubject(String subject) {
        List<Book> books = bRepos.findByBookSubjectsContainsIgnoreCase(subject);
        List<BookDto> bDtos = new ArrayList<>();
        if (books.isEmpty()) {
            throw new RecordNotFoundException("No books match the given subject, try again with a different one.");

        } else {
            for (Book b : books) {
                bDtos.add(transferToDto(b));
            }
            return bDtos;
        }

    }
    public List<BookDto> getBooksByAge(int targetAge){
        List<Book> books = bRepos.findByTargetAge(targetAge);
        List<BookDto> bDtos = new ArrayList<>();
        if (books.isEmpty()) {
            throw new RecordNotFoundException("No books match the given target age.");

        } else {
            for (Book b : books) {
                bDtos.add(transferToDto(b));
            }
            return bDtos;
        }
    }
    public List<BookDto> getBookByAuthor(String name) {
        List<Book> books = bRepos.findByAuthorContainsIgnoreCase(name);
        List<BookDto> byAuthor = new ArrayList<>();

        if (books.isEmpty()) {
            throw new RecordNotFoundException("No books match the given author's name, try again with a different one.");

        } else {
            for (Book b : books) {
                byAuthor.add(transferToDto(b));
            }
            return byAuthor;
        }
    }


    public BookDto addBook(BookDto bDto) {

        var book = transferToBook(bDto);

        bRepos.save(book);


        return transferToDto(book);
    }


    public void updateBook(Long ISBN, BookDto bDto) {
        Book bookUpdated = bRepos.findById(ISBN).orElseThrow(() -> new RecordNotFoundException("No book found, please try different ISBN"));
        bookUpdated.setISBN(bDto.getISBN());
        bookUpdated.setAuthor(bDto.getAuthor());
        bookUpdated.setTitle(bDto.getTitle());
        bookUpdated.setTargetAge(bDto.getTargetAge());
        bookUpdated.setBookSubjects(bDto.getBookSubjects());

        bRepos.save(bookUpdated);
    }

    public void deleteBook(Long ISBN) {
        bRepos.deleteById(ISBN);
    }


    //Mapping from Dto to Entity
    public Book transferToBook(BookDto bookDto) {
        var book = new Book();

        book.setISBN(bookDto.getISBN());
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        book.setTargetAge(bookDto.getTargetAge());
        book.setBookSubjects(bookDto.getBookSubjects());

        bRepos.save(book);

        return book;

    }

//mapping Entity to dto

    public BookDto transferToDto(Book book) {
        BookDto dto = new BookDto();

        dto.setISBN(book.getISBN());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setTargetAge(book.getTargetAge());
        dto.setBookSubjects(book.getBookSubjects());

        return dto;
    }
}
