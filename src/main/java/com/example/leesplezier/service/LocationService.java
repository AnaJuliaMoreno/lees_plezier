package com.example.leesplezier.service;

import com.example.leesplezier.dto.LocationDto;
import com.example.leesplezier.expection.RecordNotFoundException;
import com.example.leesplezier.model.Location;
import com.example.leesplezier.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {
    //dependency injection
    private LocationRepository lRepos;
//    private final WorkingHoursRepository whrepos;
//    private final WorkingHoursService whs;
//

    public LocationService(LocationRepository lRepos) {
        this.lRepos = lRepos;
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
        if (lRepos.findById(id).isPresent()) {
            Location loc = lRepos.findById(id).get();
            LocationDto dto = transferToDto(loc);
//            if (loc.getSchedules() != null) {
//                dto.(loc.getWorkingHoursHashSet());
//            }
            return transferToDto(loc);

        } else {
            throw new RecordNotFoundException("No location found");
        }
    }


    public LocationDto addLoc(LocationDto lDto) {
        lRepos.save(transferToLoc(lDto));

        return lDto;
    }


    // Mapping From Entity to DTO
    public LocationDto transferToDto(Location location) {

        LocationDto dto = new LocationDto();
        dto.setId(location.getId());
        dto.setNameLoc(location.getNameLoc());
        dto.setAddressLoc(location.getAddressLoc());

        return dto;
    }


    // Mapping From DTO to Entity
    public Location transferToLoc(LocationDto lDto) {
        Location loc = new Location();

        lDto.setId(loc.getId());
        loc.setNameLoc(lDto.getNameLoc());
        loc.setAddressLoc(lDto.getAddressLoc());

        lRepos.save(loc);

        return loc;
    }
}
