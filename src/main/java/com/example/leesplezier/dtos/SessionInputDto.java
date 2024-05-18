package com.example.leesplezier.dtos;

public class SessionInputDto {


    private Long id;
    private Long childId;
    private String userName;

    public SessionInputDto(Long id, Long childId, String userName) {
        this.id = id;
        this.childId = childId;
        this.userName = userName;
    }


    public SessionInputDto() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
