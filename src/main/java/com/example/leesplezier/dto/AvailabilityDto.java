package com.example.leesplezier.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class AvailabilityDto {
    private Long id;

    @NotBlank(message = "please enter a valid weekday")
    private String day;

    @Range(min = 900, max = 1700, message = "")
    private int startAt;// time given in military style: 900 = 9am.

    public AvailabilityDto() {
    }

    public AvailabilityDto(Long id, String day, int startAt) {
        this.id = id;
        this.day = validDay(day);
        this.startAt = startAt;
    }

    public String validDay(String day) {
        if (day.equalsIgnoreCase("monday") ||
                day.equalsIgnoreCase("tuesday") ||
                day.equalsIgnoreCase("wednesday") ||
                day.equalsIgnoreCase("thursday")
                || day.equalsIgnoreCase("friday") ||
                day.equalsIgnoreCase("saturday") ||
                day.equalsIgnoreCase("sunday")) {
            return day;
        } else {
            return "";
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
