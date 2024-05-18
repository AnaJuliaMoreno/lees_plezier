package com.example.leesplezier.controllers;

import com.example.leesplezier.dtos.ScheduleDto;
import com.example.leesplezier.services.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    //dependency injection
    private final ScheduleService sService;

    public ScheduleController(ScheduleService sService){
        this.sService = sService;
    }

    @GetMapping
    public ResponseEntity<List<ScheduleDto>> getAllSchedule(){
        List<ScheduleDto> sDtos = sService.getAllSchedule();
        return ResponseEntity.ok(sDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDto> getScheduleById(@PathVariable("id") int id){
        ScheduleDto schedule = sService.getScheduleById(id);
        return ResponseEntity.ok().body(schedule);
    }

    @PostMapping
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
    //Deleting a schedule is not necessary, and it'll be too much work it is not possible to do so if it has already been paired to a Location.

//    @DeleteMapping("/schedule/{id}")
//    public ResponseEntity<Object> deleteSchedule(@PathVariable("id") int id){
//        sService.deleteSchedule(id);
//        return ResponseEntity.noContent().build();
//    }
@PutMapping("/{id}")
    public ResponseEntity<ScheduleDto> updateSchedule(@PathVariable("id") int id, @RequestBody ScheduleDto scheduleDto){
        sService.updateSchedule(id, scheduleDto);
        return ResponseEntity.ok(scheduleDto);
    }

}
