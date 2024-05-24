package com.example.leesplezier.dtos;

import com.example.leesplezier.models.Availability;

import java.time.LocalDate;
import java.time.LocalTime;

public class SessionDto {
    private Long id;
    private LocalDate sessionDate;
    private String volunteersName;
    private LocalTime startTime;
    private String locationName;
    private String username;
    private String comment;


    public SessionDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getVolunteersName() {
        return volunteersName;
    }

    public void setVolunteersName(String volunteersName) {
        this.volunteersName = volunteersName;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }


    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
