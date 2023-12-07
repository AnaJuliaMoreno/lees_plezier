package com.example.leesplezier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "loc_generator")
    @SequenceGenerator(name = "loc_generator", sequenceName = "location_seq", allocationSize = 1)
    private Long id;
    private String nameLoc;
    private String addressLoc;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "location_schedule",
            joinColumns = @JoinColumn(name = "location_id"),
            inverseJoinColumns = @JoinColumn(name = "schedule_id"))
    private Set<Schedule> locationSchedules = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "child_id")
    private Child child;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameLoc() {
        return nameLoc;
    }

    public void setNameLoc(String nameLoc) {
        this.nameLoc = nameLoc;
    }

    public String getAddressLoc() {
        return addressLoc;
    }

    public void setAddressLoc(String addressLoc) {
        this.addressLoc = addressLoc;
    }

    public Set<Schedule> getLocationSchedules() {
        return locationSchedules;
    }

    public void setLocationSchedules(Set<Schedule> locationSchedules) {
        this.locationSchedules = locationSchedules;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }
}

