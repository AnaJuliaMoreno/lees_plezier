package com.example.leesplezier.service;

import com.example.leesplezier.dto.LocationDto;
import com.example.leesplezier.dto.ScheduleDto;
import com.example.leesplezier.exception.RecordNotFoundException;
import com.example.leesplezier.model.Location;
import com.example.leesplezier.model.Schedule;
import com.example.leesplezier.repository.LocationRepository;
import com.example.leesplezier.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LocationService {
    //dependency injection
    private LocationRepository lRepos;
    private ScheduleRepository sRepos;
    private ScheduleService sService;

    public LocationService(LocationRepository lRepos, ScheduleRepository sRepos, ScheduleService sService) {
        this.lRepos = lRepos;
        this.sRepos = sRepos;
        this.sService = sService;
    }


    public List<LocationDto> getAllLoc() {
        Iterable<Location> locList = lRepos.findAll();

        List<LocationDto> lDtos = new ArrayList<>();
        for (Location location : locList) {
            lDtos.add(transferToDto(location));
        }

        return lDtos;
    }

    public LocationDto getLocationById(Long id) {


        Optional<Location> location = lRepos.findById(id);
        if (location.isPresent()) {
            LocationDto locationDto = transferToDto(location.get());
            return locationDto;
        } else {
            throw new RecordNotFoundException("No location found, try a different ID.");

        }
    }


    public LocationDto addLoc(LocationDto lDto) {
        Location location = transferToLoc(lDto);
        lRepos.save(location);

        return transferToDto(location);
    }

    public void assignLocationSchedule(Long locationId, Long scheduleId) {
        Set<Schedule> schedules = new HashSet<>();
        var optionalLocation = lRepos.findById(locationId);
        var optionalSchedule = sRepos.findById(scheduleId);
        if (optionalLocation.isPresent() && optionalSchedule.isPresent()) {
            var location = optionalLocation.get();
            var schedule = optionalSchedule.get();
            schedules.add(schedule);
            location.setLocationSchedules(schedules);
            lRepos.save(location);


        } else {
            throw new RecordNotFoundException("No matching location-schedule found.");
        }
    }

    public void assignLocationSchedules(Long id, Set<ScheduleDto> schedules) {
        var optionalLocation = lRepos.findById(id);
        if (optionalLocation.isPresent()) {
            var location = optionalLocation.get();
            if (location.getLocationSchedules().isEmpty()) {
                location.setLocationSchedules(sService.transferToEntityList(schedules));
                lRepos.save(location);
            } else {
                for (ScheduleDto s : schedules) {
                    location.getLocationSchedules().add(sService.transferToSchedule(s));
                    lRepos.save(location);
                }
            }

        } else {
            throw new RecordNotFoundException("Location id has not been found");
        }
    }

//        Set<Schedule> schedules = null;
//
//        Location location = lRepos.findById(locationId).get();
//        Schedule schedule = srepos.findById(scheduleId).get();
//        schedules = location.getLocationSchedules();
//      schedules.add(schedule);
//        location.setLocationSchedules(schedules);
//        lRepos.save(location);
//
//        return transferToDto(location);


    public void deleteLocation(Long id) {
        lRepos.deleteById(id);
    }

    public void deleteScheduleIdByLocationId(Long id, Long scheduleId) {
        var optionalLocation = lRepos.findById(id);

        if (optionalLocation.isPresent()) {
            var location = optionalLocation.get();
            Set<Schedule> schedules = location.getLocationSchedules();
            schedules.removeIf(schedule -> schedule.getId().equals(scheduleId));
            lRepos.save(location);
        } else {
            throw new RecordNotFoundException("Please try again");
        }
    }

    // Mapping From Entity to DTO
    public LocationDto transferToDto(Location location) {

        LocationDto dto = new LocationDto();
        dto.setId(location.getId());
        dto.setNameLoc(location.getNameLoc());
        dto.setAddressLoc(location.getAddressLoc());
        dto.setLocationSchedules(location.getLocationSchedules());


        return dto;
    }


    // Mapping From DTO to Entity
    //Mapping from Dto to Entity -id is a @GeneratedValue.
    public Location transferToLoc(LocationDto lDto) {
        Location loc = new Location();

        loc.setNameLoc(lDto.getNameLoc());
        loc.setAddressLoc(lDto.getAddressLoc());
        loc.setLocationSchedules(lDto.getLocationSchedules());
        lRepos.save(loc);
        lDto.setId(loc.getId());


        return loc;
    }
}
