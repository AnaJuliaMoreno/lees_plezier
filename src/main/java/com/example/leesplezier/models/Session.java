package com.example.leesplezier.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
public class Session {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "session_generator")
    @SequenceGenerator(name = "session_generator", sequenceName = "session_seq", allocationSize = 1)
    private Long id;
    private LocalDate sessionDate;

private String volunteersName;

private LocalTime startTime;

    @ManyToOne
    @JoinColumn(name = "location_id")
private Location location;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String comment;

    public Session() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }



    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getVolunteersName() {
        return volunteersName;
    }

    public void setVolunteersName(String volunteersName) {
        this.volunteersName = volunteersName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
