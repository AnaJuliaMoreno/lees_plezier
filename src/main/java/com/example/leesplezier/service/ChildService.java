package com.example.leesplezier.service;

import com.example.leesplezier.dto.ChildDto;
import com.example.leesplezier.model.Child;
import com.example.leesplezier.repository.ChildRepository;
import org.springframework.stereotype.Service;

@Service
public class ChildService {
    private ChildRepository chRepos;
    private ChildService(ChildRepository chRepos){
        this.chRepos = chRepos;
    }

    //mapping entity to dto
    public Child transferToDto (ChildDto childDto){
        Child child = new Child();
        child.setId(childDto.getId());
        child.setName(childDto.getName());
        child.setGrade(childDto.getGrade());
        child.setAge(childDto.getAge());

        return child;
    }

    //mapping dto to entity
    public ChildDto transferToDto (Child child){
        ChildDto childDto = new ChildDto();

        childDto.setId(child.getId());
        childDto.setName(child.getName());
        childDto.setAge(child.getAge());
        childDto.setGrade(child.getGrade());

        return childDto;
    }
}
