package com.example.leesplezier.dtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import java.time.LocalTime;

public class AvailabilityDto {

    @NotBlank(message = "please enter a valid weekday")
    private String day;


    private LocalTime startAt;

    public AvailabilityDto() {
    }

    public AvailabilityDto(String day, LocalTime startAt) {
        this.day = day;
        this.startAt = startAt;
    }


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
