package com.example.leesplezier.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.util.HashSet;
import java.util.Set;
;

@Entity
public class ReadingFocus {

    @Id
    private String name;
    private String description;
    private String usefulInfo;

    //Relationships
    @ManyToMany(mappedBy = "childFocus")
    @JsonIgnore
    private Set<Child> children = new HashSet<>();

    //No constructors
    //Getters and Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUsefulInfo(String usefulInfo) {
        this.usefulInfo = usefulInfo;
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


    public Set<Child> getChildren() {
        return children;
    }

    public void setChildren(Set<Child> children) {
        this.children = children;
    }
}
