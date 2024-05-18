package com.example.leesplezier.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Session {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "session_generator")
    @SequenceGenerator(name = "session_generator", sequenceName = "session_seq", allocationSize = 1)
    private Long id;
    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name = "child_id")
    private Child child;



    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    public Session() {
    }


    //Getters and setters
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

}
