package com.example.leesplezier.models;

import jakarta.persistence.*;

import java.time.LocalTime;


@Embeddable
public class Availability {
    private String day;

    private LocalTime startAt;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public LocalTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalTime startAt) {
        this.startAt = startAt;
    }
}
