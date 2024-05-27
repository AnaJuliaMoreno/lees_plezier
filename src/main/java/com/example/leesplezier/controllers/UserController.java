package com.example.leesplezier.controllers;


import com.example.leesplezier.dtos.ChildDto;
import com.example.leesplezier.dtos.UserDto;
import com.example.leesplezier.exceptions.BadRequestException;
import com.example.leesplezier.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = userService.getAllUsers();
        return ResponseEntity.ok().body(userDtos);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable("username") String username) {
        UserDto user = userService.getUser(username);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/volunteers")
    public ResponseEntity<List<UserDto>> getAllVolunteerUsers() {
        List<UserDto> volunteerUsers = userService.getAllVolunteerUsers();
        return ResponseEntity.ok(volunteerUsers);
    }

    @GetMapping("/parents")
    public ResponseEntity<List<UserDto>> getAllParentUsers() {
        List<UserDto> parentUsers = userService.getAllParentUsers();
        return ResponseEntity.ok(parentUsers);
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDto userDto, BindingResult br) {
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
            userService.createUser(userDto);
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/" + userDto.getId()).toUriString());

            return ResponseEntity.created(uri).body(userDto);
        }
    }

    @PutMapping(value = "/{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("username") String username, @RequestBody UserDto dto) {

        userService.updateUser(username, dto);

        return ResponseEntity.noContent().build();

    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

}