package com.example.leesplezier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;
;

@Entity
public class ReadingFocus {
    @Id
    @GeneratedValue
   private String name;
    private String description;
    private String usefulInfo;

    @OneToMany(mappedBy = "readingFocus",fetch = FetchType.EAGER)
   @JsonIgnore
    Collection<Child> children;

    @OneToMany(mappedBy = "readingFocus",fetch = FetchType.EAGER)
    @JsonIgnore
    List<Book> books;


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
}
