package com.example.leesplezier.dtos;


import com.example.leesplezier.models.Availability;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;

import java.util.List;

public class ChildDto {
    private Long id;

    @NotBlank(message = "Name must be filled in.")
    private String name;

    @Range(min = 5, max = 12, message = "Participating ages are 5 to 12.")
    private int age;

    @NotEmpty(message = "Please select at least one location.")
    private List<String> locationsList;

    @NotEmpty(message = "Fill your availability.")
    private List<AvailabilityDto> availabilityList;

    @NotEmpty(message = "Please select at least one focus.")
    private List<String> focusList;


    public ChildDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public List<String> getLocationsList() {
        return locationsList;
    }

    public void setLocationsList(List<String> locationsList) {
        this.locationsList = locationsList;
    }

    public List<AvailabilityDto> getAvailabilityList() {
        return availabilityList;
    }

    public void setAvailabilityList(List<AvailabilityDto> availabilityList) {
        this.availabilityList = availabilityList;
    }

    public List<String> getFocusList() {
        return focusList;
    }

    public void setFocusList(List<String> focusList) {
        this.focusList = focusList;
    }
}
