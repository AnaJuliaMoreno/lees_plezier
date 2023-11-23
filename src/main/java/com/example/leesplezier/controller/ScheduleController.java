package com.example.leesplezier.controller;

import com.example.leesplezier.dto.ScheduleDto;
import com.example.leesplezier.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ScheduleController {
    //dependency injection
    private final ScheduleService sService;

    public ScheduleController(ScheduleService sService){
        this.sService = sService;
    }

    @GetMapping("/schedule")
    public ResponseEntity<List<ScheduleDto>> getAllSchedule(){
        List<ScheduleDto> sDtos = sService.getAllSchedule();
        return ResponseEntity.ok(sDtos);
    }

    @PostMapping("/schedule")
    public ResponseEntity<Object> addSchedule(@Valid @RequestBody ScheduleDto dto, BindingResult br) {
        if (br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField());
                sb.append(" : ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        } else {

            sService.addSchedule(dto);
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/" + dto.id).toUriString());

            return ResponseEntity.created(uri).body(dto);
        }
    }
    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<Object> deleteSchedule(@PathVariable("id") Long id){
        sService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
@PutMapping("schedule/{id}")
    public ResponseEntity<ScheduleDto> updateSchedule(@PathVariable("id") Long id, @RequestBody ScheduleDto scheduleDto){
        sService.updateSchedule(id, scheduleDto);
        return ResponseEntity.ok(scheduleDto);
    }


}
