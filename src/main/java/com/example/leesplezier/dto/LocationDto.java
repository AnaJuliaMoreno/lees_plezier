package com.example.leesplezier.dto;


import com.example.leesplezier.model.Schedule;

import java.util.Set;

public class LocationDto {
    private Long id;
    private String nameLoc;
    private String addressLoc;
    private Set<Schedule> locationSchedules;

    //Default Constructor
    public LocationDto() {
    }


    //Getters and Setters


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
}
