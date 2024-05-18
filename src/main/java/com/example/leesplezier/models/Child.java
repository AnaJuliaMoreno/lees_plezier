package com.example.leesplezier.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ch_generator")
    @SequenceGenerator(name = "ch_generator", sequenceName = "child_seq", allocationSize = 1)
    private Long id;
    private String name;
    private int age;
    private int grade;

    // Relationships
    @ManyToMany
    @JoinTable(name = "child_focus",
            joinColumns = @JoinColumn(name = "child_id"),
            inverseJoinColumns = @JoinColumn(name = "reading_focus_name"))
    private List<ReadingFocus> childFocus;

    @ElementCollection
    private List<Availability> availabilityList;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;



    //Models don't need constructors
    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public List<ReadingFocus> getChildFocus() {
        return childFocus;
    }

    public void setChildFocus(List<ReadingFocus> childFocus) {
        this.childFocus = childFocus;
    }

    public List<Availability> getAvailabilityList() {
        return availabilityList;
    }

    public void setAvailabilityList(List<Availability> availabilityList) {
        this.availabilityList = availabilityList;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
