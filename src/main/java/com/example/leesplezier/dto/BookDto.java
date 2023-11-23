package com.example.leesplezier.dto;

public class BookDto {
    private Long ISBN;
    private String title;
    private String author;
    private String ageRange;

    //Constructors
    public BookDto() {
    }

    public BookDto(Long ISBN, String title, String author, String ageRange) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.ageRange = ageRange;
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

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }
}
