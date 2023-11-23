package com.example.leesplezier.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@IdClass(AuthorityKey.class)
@Table(name = "roles")
public class Role implements Serializable {
    @Id
    @Column(nullable = false)
    private String username;

    @Id
    @Column(nullable = false)
    private String role;

   @ManyToMany(mappedBy = "roles")
    private Set<User> users;


    public Role() {
    }

    public Role(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
