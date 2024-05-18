package com.example.leesplezier.models;

import jakarta.persistence.*;

import java.util.Set;


@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "loc_generator")
    @SequenceGenerator(name = "loc_generator", sequenceName = "location_seq", allocationSize = 1)
    private int id;
    private String nameLoc;
    private String addressLoc;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "location_schedule",
            joinColumns = @JoinColumn(name = "location_id"),
            inverseJoinColumns = @JoinColumn(name = "schedule_id"))
    private Set<Schedule> locationSchedules;

//Location does not need to reference Child
// it is a unidirectional relationship
//    @ManyToOne
//    @JoinColumn(name = "child_id")
//    private Child child;


    public Location() {
    }

    public Location(String nameLoc, String addressLoc) {
        this.nameLoc = nameLoc;
        this.addressLoc = addressLoc;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

//    public Child getChild() {
//        return child;
//    }
//
//    public void setChild(Child child) {
//        this.child = child;
//    }
}

