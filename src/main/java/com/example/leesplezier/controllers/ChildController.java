package com.example.leesplezier.controllers;

import com.example.leesplezier.dtos.ChildDto;
import com.example.leesplezier.services.ChildService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/children")
public class ChildController {

    //dependency injection

    private final ChildService chService;

    public ChildController(ChildService chService) {
        this.chService = chService;
    }

    @GetMapping
    public ResponseEntity<List<ChildDto>> getAllChildren() {
        List<ChildDto> childDtoList = chService.getAllChildren();
        return ResponseEntity.ok(childDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChildDto> getChildbyId(@PathVariable("id") Long id) {
        ChildDto childDto = chService.getChild(id);
        return ResponseEntity.ok().body(childDto);
    }

    @PostMapping
    public ResponseEntity<Object> addChild(@Valid @RequestBody ChildDto childDto, BindingResult br) {
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
            chService.addChild(childDto);
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/" + childDto.getId()).toUriString());

            return ResponseEntity.created(uri).body(childDto);
        }
    }

    @PutMapping("/{id}/locations/{locationName}")
    public ResponseEntity<Object> assignLocationToChild(@PathVariable("id") Long id, @PathVariable("locationName") String name) {

        chService.assignLocation(id, name);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/reading_focus/{name}")
    public ResponseEntity<Object> assignFocusToChild(@PathVariable("id") Long id, @PathVariable("name") String name) {

        chService.assignFocus(id, name);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateChild(@PathVariable("id") Long id, @RequestBody @Valid ChildDto childDto) {
        chService.updateChild(id, childDto);
        return ResponseEntity.ok(childDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteChild(@PathVariable("id") Long id) {
        chService.deleteChild(id);
        return ResponseEntity.noContent().build();
    }
}
