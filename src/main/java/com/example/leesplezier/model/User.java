package com.example.leesplezier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;


    private String password;

    private String lastName;
    private String firstName;
    private String email;
    private int phone;
    @ManyToMany(fetch = FetchType.EAGER)
   private Collection<Role> roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonIgnore
   private Collection<Session> sessions;



//    @OneToMany(
//            targetEntity = Role.class,
//            mappedBy = "username",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true,
//            fetch = FetchType.EAGER)
//    private Set<Role> Roles = new HashSet<>();

    //    @OneToMany(targetEntity = Role.class,
//            mappedBy = "username",
//
//            orphanRemoval = true,
//            fetch = FetchType.EAGER)


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Collection<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Collection<Session> sessions) {
        this.sessions = sessions;
    }
}


