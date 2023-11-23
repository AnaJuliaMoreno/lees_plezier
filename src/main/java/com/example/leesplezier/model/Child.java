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

    @ManyToMany(mappedBy = "children")
    HashSet<ReadingFocus> readingGoals;


//    private Set<Availability> availabilityList = new HashSet<>();
//    private Set<Location> preferredLocations = new HashSet<>();


    //     this.readingGoals = readingGoals;
//        this.availabilityList = availabilityList;
//        this.preferredLocations = preferredLocations;

}
