package com.example.leesplezier.dtos;

import com.example.leesplezier.models.Availability;

import java.time.LocalDate;
import java.util.List;

public class SessionDto {
private Long id;
   private String username;

    private LocalDate creationDate;

    private String childName;

    private String locationName;
    private List<Availability> availabilityList;

    public SessionDto() {
    }

    public SessionDto(Long id, String username, LocalDate creationDate, String childName, String locationName, List<Availability> availabilityList) {
        this.id = id;
        this.username = username;
        this.creationDate = creationDate;
        this.childName = childName;
        this.locationName = locationName;
        this.availabilityList = availabilityList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getDate() {
        return creationDate;
    }

    public void setDate(LocalDate date) {
        this.creationDate = date;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public List<Availability> getAvailabilityList() {
        return availabilityList;
    }

    public void setAvailabilityList(List<Availability> availabilityList) {
        this.availabilityList = availabilityList;
    }
}
