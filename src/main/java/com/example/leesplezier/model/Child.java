package com.example.leesplezier.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table
public class Child {
    @Id
    private String name;
    private LocalDate dob;
    private int grade;

    @ManyToOne
    @JoinColumn(name = "reading_focus_name", nullable = false)
    private ReadingFocus readingFocus;

    //Models have no constructors
    //Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public ReadingFocus getReadingFocus() {
        return readingFocus;
    }

    public void setReadingFocus(ReadingFocus readingFocus) {
        this.readingFocus = readingFocus;
    }
}
