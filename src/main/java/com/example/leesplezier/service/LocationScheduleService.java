//package com.example.leesplezier.service;
//
//import com.example.leesplezier.dto.LocationDto;
//import com.example.leesplezier.dto.ScheduleDto;
//import com.example.leesplezier.expection.RecordNotFoundException;
//import com.example.leesplezier.model.Location;
//import com.example.leesplezier.model.LocationSchedule;
//import com.example.leesplezier.model.LocationScheduleKey;
//import com.example.leesplezier.model.Schedule;
//import com.example.leesplezier.repository.LocationRepository;
//import com.example.leesplezier.repository.LocationScheduleRepository;
//import com.example.leesplezier.repository.ScheduleRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Service
//public class LocationScheduleService {
//    private LocationRepository locRepos;
//    private ScheduleRepository sRepos;
//
//    private LocationScheduleRepository locationScheduleRepos;
//
//    public LocationScheduleService(LocationRepository locRepos, ScheduleRepository sRepos, LocationScheduleRepository locationScheduleRepos) {
//        this.locRepos = locRepos;
//        this.sRepos = sRepos;
//        this.locationScheduleRepos = locationScheduleRepos;
//    }
//
//    public Collection<LocationDto> getLocationByScheduleId(Long scheduleId) {
//
//        Collection<LocationDto> lDtos = new HashSet<>();
//        Collection<LocationSchedule> locationSchedules = locationScheduleRepos.findAllByScheduleId(scheduleId);
//
//        for (LocationSchedule locSch : locationSchedules) {
//            Location location = locSch.getLocation();
//
//            LocationDto locationDto = new LocationDto();
//            locationDto.setId(location.getId());
//            locationDto.setNameLoc(location.getNameLoc());
//            locationDto.setAddressLoc(location.getAddressLoc());
//
//            lDtos.add(locationDto);
//
//        }
//        return lDtos;
//    }
////    public List<LocationDto> findAllByLocation(){
////        Collection<LocationDto> lDtos = new HashSet<>();
////        Collection<LocationSchedule> locationSchedules = locationScheduleRepos.findAll();
////        for(LocationSchedule ls: locationSchedules.){
////            lDtos.add(getScheduleByLocationId(ldtos.
////        }
////    }
//
//    public Collection<ScheduleDto> getScheduleByLocationId(Long locationId) {
//        //Create a new list of dtos. / collection or set to avoid double entries
//        //Create a list of the combination class where the method is called from the
//        // repository and the id parameter is passed.
//        //
//        // go through all the elements of the combination class
//        //Retrieve the instance from the combination class.
//        // create a new dto
//        // retrieve/ map all the properties
//        //save/add the mapped dto to the list of dtos
//
//        //return the list of dtos.
//
//        Set<ScheduleDto> sDtos = new HashSet<>();
//
//        List<LocationSchedule> locationSchedules = locationScheduleRepos.findAllByLocationId(locationId);
//        for (LocationSchedule ls : locationSchedules) {
//            var schedule = ls.getSchedule();
//
//            ScheduleDto scheduleDto = new ScheduleDto();
//            scheduleDto.setId(schedule.getId());
//            scheduleDto.setOpensAt(schedule.getOpensAt());
//            scheduleDto.setClosesAt(schedule.getClosesAt());
//            scheduleDto.setDayOfWeek(schedule.getDayOfWeek());
//
//            sDtos.add(scheduleDto);
//
//        }
//        return sDtos;
//    }
//
//
//
//    public LocationScheduleKey addLocationSchedule(Long locationId, Long scheduleId) {
//
//        var locationSchedule = new LocationSchedule();
//
//        if (!locRepos.existsById(locationId)) {
//            throw new RecordNotFoundException("Location not found");
//        }
//        Location location = locRepos.findById(locationId).orElse(null);
//
//        if (!sRepos.existsById(scheduleId)) {
//            throw new RecordNotFoundException("Schedule not found");
//        }
//        Schedule schedule = sRepos.findById(scheduleId).orElse(null);
//
//        locationSchedule.setLocation(location);
//        locationSchedule.setSchedule(schedule);
//        LocationScheduleKey id = new LocationScheduleKey(locationId, scheduleId);
//        locationSchedule.setId(id);
//        locationScheduleRepos.save(locationSchedule);
//        return id;
//    }
//
//}
