package com.example.leesplezier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "schedule")
public class Schedule {


    //Without the change in sequence value for the ID, default value generated has increments of 50

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "s_generator")
    @SequenceGenerator(name = "s_generator", sequenceName = "schedule_seq", allocationSize = 1)
    private Long id;


    private String dayOfWeek;

    private int opensAt; //in minutes military style ex: 900 = 9:00

    private int closesAt;  //in minutes military style ex: 900 = 9:00
    @OneToMany(mappedBy = "schedule")
    @JsonIgnore
    List<LocationSchedule> locationScheduleList;


//    @ManyToOne
//    @MapsId("locationId")
//    @JoinColumn(name = "location_id")
//    private Location locations;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

//    public Location getLocation() {
//        return location;
//    }
//
//    public void setLocation(Location location) {
//        this.location = location;
//    }



    public List<LocationSchedule> getLocationScheduleList() {
        return locationScheduleList;
    }

    public void setLocationScheduleList(List<LocationSchedule> locationScheduleList) {
        this.locationScheduleList = locationScheduleList;
    }
}


