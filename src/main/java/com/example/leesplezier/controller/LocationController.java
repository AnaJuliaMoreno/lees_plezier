package com.example.leesplezier.controller;

import com.example.leesplezier.dto.LocationDto;
import com.example.leesplezier.service.LocationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<LocationDto> getLocation(@PathVariable("id") Long id){
        LocationDto loc = locService.getLocationById(id);
        return ResponseEntity.ok().body(loc);
    }


    @PostMapping("/location")
    public ResponseEntity<Object> addLoc(@Valid @RequestBody LocationDto dto, BindingResult br) {
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

            locService.addLoc(dto);
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/" + dto.getId()).toUriString());

            return ResponseEntity.created(uri).body(dto);
        }
    }

}
