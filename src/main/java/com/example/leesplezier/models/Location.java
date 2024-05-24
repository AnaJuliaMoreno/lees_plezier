package com.example.leesplezier.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "loc_generator")
    @SequenceGenerator(name = "loc_generator", sequenceName = "location_seq", allocationSize = 1)
    private int id;
    private String name;
    private String addressLoc;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "location_schedule",
            joinColumns = @JoinColumn(name = "location_id"),
            inverseJoinColumns = @JoinColumn(name = "schedule_id"))
    private Set<Schedule> locationSchedules;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Session> sessions = new HashSet<>();


    public Location() {
    }

    public Location(int id, String name, String addressLoc, Set<Schedule> locationSchedules, Set<Session> sessions) {
        this.id = id;
        this.name = name;
        this.addressLoc = addressLoc;
        this.locationSchedules = locationSchedules;
        this.sessions = sessions;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }
}

