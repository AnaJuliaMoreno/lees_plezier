package com.example.leesplezier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Book {

    @Id
    private Long ISBN;
    private String title;
    private String author;
    private int targetAge;

    @ManyToMany
    @JoinTable(name = "book_focus",
            joinColumns = @JoinColumn(name = "ISBN"),
            inverseJoinColumns = @JoinColumn(name = "reading_focus_name"))
    private Set<ReadingFocus> bookFocus = new HashSet<>();

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

    public Set<ReadingFocus> getBookFocus() {
        return bookFocus;
    }

    public void setBookFocus(Set<ReadingFocus> bookFocus) {
        this.bookFocus = bookFocus;
    }
}
