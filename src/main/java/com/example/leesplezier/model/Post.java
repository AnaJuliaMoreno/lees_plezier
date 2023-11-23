package com.example.leesplezier.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Set;
@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    //To include
//    private User user;
//    private Child child;
//    private Location location;
//    private Set<Availability> availabilitySet;
}
