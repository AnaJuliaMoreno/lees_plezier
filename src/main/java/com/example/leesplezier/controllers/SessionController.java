package com.example.leesplezier.controllers;

import com.example.leesplezier.dtos.SessionDto;
import com.example.leesplezier.dtos.SessionInputDto;

import com.example.leesplezier.services.SessionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("sessions")
public class SessionController {
    private final SessionService sService;

    public SessionController(SessionService sService){
        this.sService =sService;
    }


    @GetMapping
    public ResponseEntity<List<SessionDto>> getAllSessions(){
        List<SessionDto> sessions = sService.getAllSessions();
        return ResponseEntity.ok().body(sessions);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<SessionDto> getSessionById(@PathVariable ("id") Long id){
        SessionDto sessionDto = sService.getSessionById(id);
        return ResponseEntity.ok().body(sessionDto);
    }

    @PostMapping("create")
    public ResponseEntity<SessionDto> createSession(@Valid @RequestBody SessionInputDto inputDto) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            inputDto.setUserName(user.getUsername());
           var sessionDto = sService.createSession(inputDto);
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/" + sessionDto.getId()).toUriString());

            return ResponseEntity.created(uri).body(sessionDto);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSession(@PathVariable("id") Long id) {


        sService.deleteSession(id);
        return new ResponseEntity<>("Session has been deleted", HttpStatus.OK);
    }

}

