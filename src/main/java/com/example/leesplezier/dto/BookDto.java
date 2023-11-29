package com.example.leesplezier.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;

public class BookDto {

    //Constraint: ISBN must have 13 digits
    @Digits(integer = 13, fraction = 0)
    private Long ISBN;
    @Size(min = 3)
    private String title;
    @NotNull
    private String author;
    @Range(min = 4, max = 12)
    private int targetAge;


    //Constructors
    public BookDto() {
    }

    public BookDto(Long ISBN, String title, String author, int targetAge) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.targetAge = targetAge;
    }

//Getters and Setters
    public Long getISBN() {
        return ISBN;
    }

    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getTargetAge() {
        return targetAge;
    }

    public void setTargetAge(int targetAge) {
        this.targetAge = targetAge;
    }
}
