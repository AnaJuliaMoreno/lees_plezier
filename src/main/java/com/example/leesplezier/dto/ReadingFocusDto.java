package com.example.leesplezier.dto;

public class ReadingFocusDto {
    private String name;
    private String description;
    private String usefulInfo;

    private BookDto bookDto;
    private ChildDto childDto;

    //Constructors
    public ReadingFocusDto() {
    }

    public ReadingFocusDto(String name, String description, String usefulInfo) {
        this.name = name;
        this.description = description;
        this.usefulInfo = usefulInfo;
    }
//Getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsefulInfo() {
        return usefulInfo;
    }

    public void setUsefulInfo(String usefulInfo) {
        this.usefulInfo = usefulInfo;
    }

    public BookDto getBookDto() {
        return bookDto;
    }

    public void setBookDto(BookDto bookDto) {
        this.bookDto = bookDto;
    }

    public ChildDto getChildDto() {
        return childDto;
    }

    public void setChildDto(ChildDto childDto) {
        this.childDto = childDto;
    }
}
