package com.example.leesplezier.controllers;

import com.example.leesplezier.dtos.ReadingFocusDto;
import com.example.leesplezier.services.ReadingFocusService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/reading_focus")
public class ReadingFocusController {
    private final ReadingFocusService rfService;

    public ReadingFocusController(ReadingFocusService rfService) {
        this.rfService = rfService;
    }

    @GetMapping
    public ResponseEntity<List<ReadingFocusDto>> getAllRF() {
        List<ReadingFocusDto> rfDtos = rfService.getAllReadingFoci();
        return ResponseEntity.ok(rfDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadingFocusDto> getRFbyName(@PathVariable("id") String id) {
        ReadingFocusDto readingFocusDto = rfService.getReadingFocusByName(id);

        return ResponseEntity.ok().body(readingFocusDto);
    }

    @PostMapping
    public ResponseEntity<Object> addReadingFocus(@Valid @RequestBody ReadingFocusDto rfDto, BindingResult br) {
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

            rfService.addReadingFocus(rfDto);
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/" + rfDto.getName()).toUriString());

            return ResponseEntity.created(uri).body(rfDto);
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<ReadingFocusDto> updateRF(@PathVariable("name") String name, @RequestBody ReadingFocusDto readingFocusDto) {
        rfService.updateRF(name, readingFocusDto);
        return ResponseEntity.ok(readingFocusDto);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Object> deleteReadingFocus(@PathVariable("name") String name) {
        rfService.deleteReadingFocus(name);
        return ResponseEntity.noContent().build();
    }
}
