package com.example.leesplezier.dtos;


import com.example.leesplezier.models.Schedule;

import java.util.Set;

public class LocationDto {
    private int id;
    private String name;
    private String addressLoc;
    private Set<Schedule> locationSchedules;

    //Default Constructor
    public LocationDto() {
    }

    public LocationDto(String name, String addressLoc) {
        this.name = name;
        this.addressLoc = addressLoc;
    }
    //Getters and Setters


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
}
