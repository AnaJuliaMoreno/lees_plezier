package com.example.leesplezier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import java.util.Collection;


@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "loc_generator")
    @SequenceGenerator(name = "loc_generator", sequenceName = "location_seq", allocationSize = 1)
    private Long id;
    private String nameLoc;
    private String addressLoc;


    @OneToMany(mappedBy = "location", fetch = FetchType.EAGER)
    @JsonIgnore
   private Collection<LocationSchedule> locationSchedules;

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

    public Collection<LocationSchedule> getLocationSchedules() {
        return locationSchedules;
    }

    public void setLocationSchedules(Collection<LocationSchedule> locationSchedules) {
        this.locationSchedules = locationSchedules;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }
}

