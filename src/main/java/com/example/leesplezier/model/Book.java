package com.example.leesplezier.model;
import jakarta.persistence.*;


@Entity
public class Book {

    @Id
    private Long ISBN;
    private String title;
    private String author;
    private String ageRange;


    @ManyToOne
    @JoinColumn(name = "reading_focus_name", nullable = false)
    private ReadingFocus readingFocus;

    public  Long getISBN() {
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


}
