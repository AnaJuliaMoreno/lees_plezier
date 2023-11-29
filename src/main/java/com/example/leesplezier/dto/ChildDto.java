package com.example.leesplezier.dto;


import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;

public class ChildDto {
    private Long id;

   @NotBlank(message = "Name must be filled in.")
    private String name;

   @Range(min = 6, max = 12, message = "Participating ages are 6 to 12")
   private int age;

   @Range(min = 4, max = 8, message = "Participating grades are from 4th to 8th" )
   private int grade;

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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
