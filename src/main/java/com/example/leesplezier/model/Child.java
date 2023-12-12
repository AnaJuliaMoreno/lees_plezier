package com.example.leesplezier.model;

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
    private Set<ReadingFocus> childFocus = new HashSet<>();

    @OneToMany(mappedBy = "child")
    private Set<Availability> availabilityChild;

    @OneToMany(mappedBy = "child")
    private Set<Location> preferredLocation;

    @OneToMany(mappedBy = "child", fetch = FetchType.EAGER)
    @JsonIgnore
    private Collection<Session> sessions;


    //Models have no constructors
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

    public Set<ReadingFocus> getChildFocus() {
        return childFocus;
    }

    public void setChildFocus(Set<ReadingFocus> childFocus) {
        this.childFocus = childFocus;
    }

    public Set<Availability> getAvailabilityChild() {
        return availabilityChild;
    }

    public void setAvailabilityChild(Set<Availability> availabilityChild) {
        this.availabilityChild = availabilityChild;
    }

    public Set<Location> getPreferredLocation() {
        return preferredLocation;
    }

    public void setPreferredLocation(Set<Location> preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public Collection<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Collection<Session> sessions) {
        this.sessions = sessions;
    }
}
