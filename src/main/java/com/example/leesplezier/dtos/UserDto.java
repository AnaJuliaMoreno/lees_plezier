package com.example.leesplezier.dtos;

import com.example.leesplezier.models.Role;
//import com.example.leesplezier.models.Role1;

import java.time.LocalDateTime;
import java.util.Set;

public class UserDto {
private Long id;
    private String username;
    private String lastName;

    private String password;
    private String email;

    private Role role;

    public UserDto() {
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


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

