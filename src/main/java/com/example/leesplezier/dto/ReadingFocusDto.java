package com.example.leesplezier.dto;

import com.example.leesplezier.model.Book;
import com.example.leesplezier.model.Child;

import java.util.Set;

public class ReadingFocusDto {
    private String name;
    private String description;
    private String usefulInfo;

    private Set<Book> books;
//    private Set<Child> children;

    //Default Constructor
    public ReadingFocusDto() {
    }

//Getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsefulInfo() {
        return usefulInfo;
    }

    public void setUsefulInfo(String usefulInfo) {
        this.usefulInfo = usefulInfo;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    //public Set<Child> getChildren() {
//        return children;
//    }

//    public void setChildren(Set<Child> children) {
//        this.children = children;
//    }
}
