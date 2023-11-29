package com.example.leesplezier.model;

import jakarta.persistence.*;

@Entity
public class Session {
    @EmbeddedId
    private SessionKey id;

    @ManyToOne
    @MapsId("childId")
    @JoinColumn(name = "child_id", nullable = false)
    private Child child;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private boolean confirmed;

    //Getters and setters
    public SessionKey getId() {
        return id;
    }

    public void setId(SessionKey id) {
        this.id = id;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
