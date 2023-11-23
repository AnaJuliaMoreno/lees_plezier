package com.example.leesplezier.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;


//In order to define the composite primary keys, we should follow some rules:
//
//        The composite primary key class must be public.
//        It must have a no-arg constructor.
//        It must define the equals() and hashCode() methods.
//        It must be Serializable.
@Embeddable
public class LocationScheduleKey implements Serializable {
    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "schedule_id")
    private Long scheduleId;

    //Constructors
    public LocationScheduleKey() {
    }

    public LocationScheduleKey(Long locationId, Long scheduleId) {
        this.locationId = locationId;
        this.scheduleId = scheduleId;
    }


    @Override
    public int hashCode() {
        return Objects.hash(locationId, scheduleId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() !=obj.getClass()) return false;
        LocationScheduleKey that  = (LocationScheduleKey) obj;
        return locationId.equals(that.locationId) && scheduleId.equals(that.scheduleId);

    }


    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }
}
