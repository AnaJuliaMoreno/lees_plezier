package com.example.leesplezier.dtos;

import jakarta.validation.constraints.*;

public class ScheduleDto {
    public int id;

    @NotBlank(message = "please enter a valid weekday")
    private String dayOfWeek;
    @Min(value = 800, message = "Libraries are not open before 8am, choose a time between 800 (8am) and 1300(1pm)")
    @Max(value = 1300, message = "Libraries open before 1pm, choose a time between 800 (8am) and 1300(1pm)")
    private int opensAt; //in minutes military style ex: 900 = 9:00

    @Min(value = 1300, message = "Libraries don't close before 1pm, choose a time between 1300 (1pm) and 1900(7pm)")
    @Max(value = 1900, message = "Libraries don't close after 7pm, choose a time between 1300 (1pm) and 1900(7pm)")
    private int closesAt;  //in minutes military style ex: 900 = 9:00


    public ScheduleDto() {
    }

    //validation added in setter dayOfWeek
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


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public String getDayOfWeek() {
        return validDay(dayOfWeek);
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = validDay(dayOfWeek);
    }

    public int getOpensAt() {
        return opensAt;
    }

    public void setOpensAt(int opensAt) {
        this.opensAt = opensAt;
    }

    public int getClosesAt() {
        return closesAt;
    }

    public void setClosesAt(int closesAt) {
        this.closesAt = closesAt;
    }

}
