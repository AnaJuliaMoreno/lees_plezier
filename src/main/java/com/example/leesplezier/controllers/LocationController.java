package com.example.leesplezier.controllers;

import com.example.leesplezier.dtos.LocationDto;

import com.example.leesplezier.dtos.ScheduleDto;
import com.example.leesplezier.services.LocationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("locations")
public class LocationController {

    //dependency injection
    private final LocationService locService;

    public LocationController(LocationService locService) {
        this.locService = locService;
    }

    @GetMapping
    public ResponseEntity<List<LocationDto>> getAllLoc() {
        List<LocationDto> lDtos = locService.getAllLoc();
        return ResponseEntity.ok(lDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> getLocationById(@PathVariable("id") int id) {
        LocationDto loc = locService.getLocationById(id);

        return ResponseEntity.ok().body(loc);
    }

    @PutMapping("/{locationId}/schedules/{scheduleId}")
    public ResponseEntity<LocationDto> assignLocationSchedule(@PathVariable("locationId") int locationId, @PathVariable("scheduleId") int scheduleId) {

        locService.assignOneScheduleToLocation(locationId, scheduleId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{locationId}/schedules")
    public ResponseEntity<Object> assignLocationSchedulesList(@PathVariable("locationId") int locationId, @Valid @RequestBody Set<ScheduleDto> schedule) {

        locService.assignSchedulesToLocation(locationId, schedule);
        return ResponseEntity.noContent().build();
    }


    @PostMapping()
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLocation(@PathVariable("id") int id) {
        locService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/schedules/{schedulesId}")
    public ResponseEntity<Object> deleteSchedule(@PathVariable("id") int id, @PathVariable("schedulesId") int scheduleId) {
        locService.deleteScheduleIdByLocationId(id, scheduleId);
        return ResponseEntity.noContent().build();
    }

}
