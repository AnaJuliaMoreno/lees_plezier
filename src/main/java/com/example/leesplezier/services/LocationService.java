package com.example.leesplezier.services;

import com.example.leesplezier.dtos.LocationDto;
import com.example.leesplezier.dtos.ScheduleDto;
import com.example.leesplezier.exceptions.RecordNotFoundException;
import com.example.leesplezier.models.Location;
import com.example.leesplezier.models.Schedule;
import com.example.leesplezier.repositories.LocationRepository;
import com.example.leesplezier.repositories.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LocationService {
    //dependency injection
    private final LocationRepository lRepos;
    private final ScheduleRepository sRepos;
    private final ScheduleService sService;

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

    public LocationDto getLocationById(int id) {

        Optional<Location> location = lRepos.findById(id);
        if (location.isPresent()) {
            return transferToDto(location.get());

        } else {
            throw new RecordNotFoundException("No location found, try a different ID.");
        }

    }


    public LocationDto addLoc(LocationDto lDto) {
        Location location = transferToLoc(lDto);
        lRepos.save(location);

        return transferToDto(location);
    }


    public void assignOneScheduleToLocation(int locationId, int scheduleId) {
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

    public void assignSchedulesToLocation(int id, Set<ScheduleDto> schedules) {
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


    public void deleteLocation(int id) {
        lRepos.deleteById(id);
    }

    public void deleteScheduleIdByLocationId(int id, int scheduleId) {
        var optionalLocation = lRepos.findById(id);

        if (optionalLocation.isEmpty()) {
            throw new RecordNotFoundException("Location id not found");
        } else {
            var location = optionalLocation.get();

            var optionalSchedules = location.getLocationSchedules();
            if (optionalSchedules != null) {
                optionalSchedules.removeIf(s -> s.getId() == scheduleId);
                } else {
                throw new RecordNotFoundException("Schedule id not found in that location");
            }
            lRepos.save(location);

        }
    }

//            var optionalLocation = lRepos.findById(id);
//
//            if (optionalLocation.isPresent()) {
//                var location = optionalLocation.get();
//                //Set<Schedule> schedules = location.getLocationSchedules();
//                for (Schedule s : location.getLocationSchedules())
//                    if(s.getId() == scheduleId){
//                        sRepos.deleteById(scheduleId);
//                    };
//                lRepos.save(location);
//            } else {
//                throw new RecordNotFoundException("Please try again");


    // Mapping From Entity to DTO
    public static LocationDto transferToDto(Location location) {

        LocationDto dto = new LocationDto();
        dto.setId(location.getId());
        dto.setNameLoc(location.getNameLoc());
        dto.setAddressLoc(location.getAddressLoc());
        dto.setLocationSchedules(location.getLocationSchedules());


        return dto;
    }


    //Mapping from Dto to Entity -id is a @GeneratedValue .
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
