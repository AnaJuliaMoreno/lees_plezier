package com.example.leesplezier.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "schedule")
public class Schedule {


    //Without the change in sequence value for the ID, default value generated has increments of 50

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "s_generator")
    @SequenceGenerator(name = "s_generator", sequenceName = "schedule_seq", allocationSize = 1)
    private int id;
    private String dayOfWeek;
    private int opensAt; //in minutes military style ex: 900 = 9:00

    private int closesAt;  //in minutes military style ex: 900 = 9:00

// Not necessary because relationship is unidirectional

    @JsonIgnore
    @ManyToMany(mappedBy = "locationSchedules")
   private Set<Location> schedules = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
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

//    public Set<Location> getSchedules() {
//        return schedules;
//    }
//
//    public void setSchedules(Set<Location> schedules) {
//        this.schedules = schedules;
//    }
}



