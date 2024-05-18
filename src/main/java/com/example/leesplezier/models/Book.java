package com.example.leesplezier.models;

import jakarta.persistence.*;

import java.util.*;


@Entity
public class Book {

    @Id
    private Long ISBN;
    private String title;
    private String author;
    private int targetAge;

@ElementCollection
@CollectionTable(name = "book_subjects",
joinColumns = @JoinColumn(name="book_ISBN"))
    private Set<String> bookSubjects;

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

    public Set<String> getBookSubjects() {
        return bookSubjects;
    }

    public void setBookSubjects(Set<String> bookSubjects) {
        this.bookSubjects = bookSubjects;
    }
}
