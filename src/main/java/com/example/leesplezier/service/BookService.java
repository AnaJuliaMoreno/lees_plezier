package com.example.leesplezier.service;


import com.example.leesplezier.dto.BookDto;
import com.example.leesplezier.model.Book;
import com.example.leesplezier.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    //dependency injection
    private BookRepository bRepos;

    public BookService(BookRepository bRepos){
        this.bRepos = bRepos;
    }

    //Mapping from Dto to Entity
    public Book transferToBook(BookDto bookDto){
        var book = new Book();

        book.setISBN(bookDto.getISBN());
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(book.getTitle());
        book.setTargetAge(book.getTargetAge());
        return book;
    }

    //mapping Entity to dto

    public BookDto transferToDto(Book book){
        BookDto dto = new BookDto();

        dto.setISBN(book.getISBN());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setTargetAge(book.getTargetAge());

        return dto;
    }
}
