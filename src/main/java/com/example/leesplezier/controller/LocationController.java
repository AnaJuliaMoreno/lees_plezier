package com.example.leesplezier.controller;

import com.example.leesplezier.dto.LocationDto;

import com.example.leesplezier.dto.ScheduleDto;
//import com.example.leesplezier.service.LocationScheduleService;
import com.example.leesplezier.service.LocationService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import java.util.List;
import java.util.Set;


@RestController
public class LocationController {

    //dependency injection
    private final LocationService locService;

        public LocationController(LocationService locService){
        this.locService = locService;
    }

    @GetMapping("/location")
    public ResponseEntity<List<LocationDto>> getAllLoc(){
        List<LocationDto> lDtos = locService.getAllLoc();
        return ResponseEntity.ok(lDtos);
    }

    @GetMapping("location/{id}")
    public ResponseEntity<LocationDto> getLocationById(@PathVariable("id") Long id){
        LocationDto loc = locService.getLocationById(id);

        return ResponseEntity.ok().body(loc);
    }

        @PutMapping("/location/{locationId}/schedules/{scheduleId}")
    public ResponseEntity<LocationDto> assignLocationSchedule(@PathVariable("locationId")Long locationId, @PathVariable("scheduleId") Long scheduleId) {

        locService.assignLocationSchedule(locationId, scheduleId);
    return ResponseEntity.noContent().build();
    }
    @PutMapping("/location/{locationId}/schedules")
    public ResponseEntity<Object> assignLocationSchedules(@PathVariable("locationId")Long locationId, @Valid @RequestBody Set<ScheduleDto> schedule) {

        locService.assignLocationSchedules(locationId, schedule);
        return ResponseEntity.noContent().build();
    }



    @PostMapping("/location")
    public ResponseEntity<Object> addLoc(@Valid @RequestBody LocationDto ldto, BindingResult br) {
        if (br.hasFieldErrors()) {
            var sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField());
                sb.append(" : ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        } else {

            locService.addLoc(ldto);
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/" + ldto.getId()).toUriString());

            return ResponseEntity.created(uri).body(ldto);
        }
    }

    @DeleteMapping("/location/{id}")
    public ResponseEntity<Object> deleteLocation(@PathVariable("id") Long id){
        locService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/location/{id}/schedules/{schedulesId}")
    public ResponseEntity<Object> deleteSchedule(@PathVariable("id") Long id, @PathVariable ("schedulesId") Long scheduleId){
        locService.deleteScheduleIdByLocationId(id, scheduleId);
        return ResponseEntity.noContent().build();
    }

}
