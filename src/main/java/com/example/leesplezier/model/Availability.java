package com.example.leesplezier.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Entity
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long timeSlotId;
    private LocalDate avDate;
    private int startsAt;

    public Availability() {
    }

    public Availability(Long timeSlotId, LocalDate avDate, int startsAt) {

        this.timeSlotId = timeSlotId;
        this.avDate = avDate;
        this.startsAt = startsAt;
    }

    public Long getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(Long timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public LocalDate getLocalDate() {
        return avDate;
    }

    public void setLocalDate(LocalDate avDate) {
        this.avDate = avDate;
    }

    public int getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(int startsAt) {
        this.startsAt = startsAt;
    }


}
