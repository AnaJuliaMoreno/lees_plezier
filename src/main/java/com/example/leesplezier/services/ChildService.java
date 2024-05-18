package com.example.leesplezier.services;

import com.example.leesplezier.dtos.AvailabilityDto;
import com.example.leesplezier.dtos.ChildDto;
import com.example.leesplezier.exceptions.BadRequestException;
import com.example.leesplezier.exceptions.RecordNotFoundException;
import com.example.leesplezier.models.Availability;
import com.example.leesplezier.models.Child;
import com.example.leesplezier.models.ReadingFocus;
import com.example.leesplezier.repositories.ChildRepository;
import com.example.leesplezier.repositories.LocationRepository;
import com.example.leesplezier.repositories.ReadingFocusRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChildService {
    private ChildRepository chRepos;
    private LocationRepository lRepos;
    private LocationService lService;
    private ReadingFocusRepository rfRepos;
    private ReadingFocusService rfService;

    private ChildService(ChildRepository chRepos, LocationRepository lRepos, LocationService lService, ReadingFocusRepository rfRepos, ReadingFocusService rfService) {
        this.chRepos = chRepos;
        this.lRepos = lRepos;
        this.lService = lService;
        this.rfService = rfService;
        this.rfRepos = rfRepos;
    }

    public List<ChildDto> getAllChildren() {
        Iterable<Child> childList = chRepos.findAll();
        List<ChildDto> childDtos = new ArrayList<>();
        for (Child ch : childList) {
            childDtos.add(transferToDto(ch));
        }
        return childDtos;
    }

    public ChildDto getChild(Long id) {
        Child ch = chRepos.findById(id).orElseThrow(() -> new RecordNotFoundException("Child not found"));

        ChildDto childDto = transferToDto(ch);

        return childDto;
    }

    public ChildDto addChild(ChildDto childDto) {
        var child = transferToChild(childDto);

        chRepos.save(child);

        return transferToDto(child);
    }

    public void deleteChild(Long id) {
        chRepos.deleteById(id);
    }

    public void assignLocation(Long id, String locationName) {
        var optionalChild = chRepos.findById(id);
        var location = lRepos.findByNameLocEqualsIgnoreCase(locationName);

        if (optionalChild.isEmpty()) {
            throw new RecordNotFoundException("No child found with that id.");

        }
        if (location != null) {
            var child = optionalChild.get();
            child.setLocation(location);
            chRepos.save(child);
        } else {
            throw new RecordNotFoundException("No location found.");

        }
    }

    public void assignFocus(Long id, String name) {

        var child = chRepos.findById(id).orElseThrow(() -> new RecordNotFoundException("No child found with that id."));
        var optionalFocus = rfRepos.findByNameContainingIgnoreCase(name);


        if (optionalFocus != null) {
            for (ReadingFocus rf : child.getChildFocus()) {
                if (rf == optionalFocus) {
                    throw new BadRequestException("This reading focus has already been added.");
                }
            }
            child.getChildFocus().add(optionalFocus);
            chRepos.save(child);

        } else {
            throw new RecordNotFoundException("No reading focus found.");

        }
    }

    public void updateChild(Long id, ChildDto childDto) {
        Child updatedChild = chRepos.findById(id).orElseThrow(() -> new RecordNotFoundException("No child has been found with that id."));
        updatedChild.setName(childDto.getName());
        updatedChild.setGrade(childDto.getGrade());
        updatedChild.setAge(childDto.getAge());
        updatedChild.setAvailabilityList(avToEntity(childDto.getAvailabilityList()));
        chRepos.save(updatedChild);

    }


    //mapping dto to entity
    public Child transferToChild(ChildDto childDto) {
        Child child = new Child();

        child.setName(childDto.getName());
        child.setGrade(childDto.getGrade());
        child.setAge(childDto.getAge());
        child.setAvailabilityList(avToEntity(childDto.getAvailabilityList()));
        chRepos.save(child);
        childDto.setId(child.getId());

        return child;
    }

    public static List<Availability> avToEntity(List<AvailabilityDto> aDto){
        List<Availability> availabilityList = new ArrayList<>();
        for(AvailabilityDto a: aDto){
            Availability availability = new Availability();
            availability.setDay(a.getDay());
            availability.setStartAt(a.getStartAt());
            availabilityList.add(availability);
        }
        return availabilityList;
    }

    public static List<AvailabilityDto> avToDto(List<Availability> availabilityList) {
        List<AvailabilityDto> aDto = new ArrayList<>();
        for (Availability a : availabilityList) {
            AvailabilityDto dto = new AvailabilityDto(a.getDay(), a.getStartAt());
            aDto.add(dto);
        }
        return aDto;
    }


    //mapping entity to dto
    public static ChildDto transferToDto(Child child) {
        ChildDto childDto = new ChildDto();

        childDto.setId(child.getId());
        childDto.setName(child.getName());
        childDto.setAge(child.getAge());
        childDto.setGrade(child.getGrade());

        childDto.setAvailabilityList(avToDto(child.getAvailabilityList()));

        if (child.getLocation() != null) {
            childDto.setLocation(child.getLocation().getNameLoc());
        }

        if (child.getChildFocus() != null) {
            List<String> focusList = new ArrayList<>();
            for (ReadingFocus rf : child.getChildFocus()) {
                focusList.add(rf.getName());
            }
            childDto.setFocusList(focusList);
        }


        return childDto;
    }
}
