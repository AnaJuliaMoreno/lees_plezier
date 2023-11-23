package com.example.leesplezier.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class ReadingFocus {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String usefulInfo;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "child_goals")
    List<Child> children;


    public ReadingFocus() {
    }


    public ReadingFocus(Long id, String name, String description, String usefulInfo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.usefulInfo = usefulInfo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUsefulInfo(String usefulInfo) {
        this.usefulInfo = usefulInfo;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUsefulInfo() {
        return usefulInfo;
    }
}
