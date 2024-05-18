package com.example.leesplezier.models;

import jakarta.persistence.*;


@Embeddable
public class Availability {
    private String day;

    private int startAt;// time given in military style: 900 = 9am.

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getStartAt() {
        return startAt;
    }

    public void setStartAt(int startAt) {
        this.startAt = startAt;
    }

}
