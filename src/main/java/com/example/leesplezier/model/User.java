package com.example.leesplezier.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;


@Entity
@Table(name = "users")
public class User {

    @Id
    private String username;


    private String password;

    private String lastName;
    private String firstName;
    private String email;
    private int phone;
    @ManyToMany(fetch = FetchType.EAGER)
   private Collection<Role> roles;


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
//    private

//        @OneToMany
//        @JoinColumn (name = "parent_id")
//        List<Parent> parents;
//
//        @OneToMany
//        @JoinColumn (name = "volunteer_id")
//        List<Volunteer> volunteer;


    // Getterset<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }
}


