//package com.example.leesplezier.model;
//
//import jakarta.persistence.*;
//
//@Entity
//public class LocationSchedule {
//    @EmbeddedId
//    private LocationScheduleKey id;
//
//    @ManyToOne
//    @MapsId("locationId")
//    @JoinColumn(name = "location_id")
//    private Location location;
//
//    @ManyToOne
//    @MapsId("scheduleId")
//    @JoinColumn(name = "schedule_id")
//    private Schedule schedule;
//
//
//
//    public LocationScheduleKey getId() {
//        return id;
//    }
//
//    public void setId(LocationScheduleKey id) {
//        this.id = id;
//    }
//
//    public Location getLocation() {
//        return location;
//    }
//
//    public void setLocation(Location location) {
//        this.location = location;
//    }
//
//    public Schedule getSchedule() {
//        return schedule;
//    }
//
//    public void setSchedule(Schedule schedule) {
//        this.schedule = schedule;
//    }
//}
//
//
