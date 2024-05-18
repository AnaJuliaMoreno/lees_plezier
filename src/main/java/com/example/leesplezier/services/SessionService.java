package com.example.leesplezier.services;

import com.example.leesplezier.dtos.SessionDto;
import com.example.leesplezier.dtos.SessionInputDto;
import com.example.leesplezier.exceptions.BadRequestException;
import com.example.leesplezier.exceptions.RecordNotFoundException;
import com.example.leesplezier.models.Session;
import com.example.leesplezier.repositories.ChildRepository;
import com.example.leesplezier.repositories.SessionRepository;
import com.example.leesplezier.repositories.UserRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SessionService {
    private final SessionRepository sRepos;
    private final UserRepository uRepos;
    private final UserService uService;
    private final ChildService chService;
    private final ChildRepository chRepos;

    public SessionService(SessionRepository sRepos, UserRepository uRepos, UserService uService, ChildService chService, ChildRepository chRepos) {
        this.sRepos = sRepos;
        this.uRepos = uRepos;
        this.chService = chService;
        this.chRepos = chRepos;
        this.uService = uService;
    }

    public List<SessionDto> getAllSessions() {
        Iterable<Session> sessionsList = sRepos.findAll();

        List<SessionDto> sessionDtos = new ArrayList<>();
        for (Session session : sessionsList) {
            sessionDtos.add(transferToDto(session));
        }
        return sessionDtos;
    }

    public SessionDto getSessionById(Long id) {
        if (!sRepos.existsById(id)) throw new RecordNotFoundException("No session found");

        var optionalSession = sRepos.findById(id).get();
        return transferToDto(optionalSession);

    }

    public SessionDto createSession(SessionInputDto dto) {

        Session session = transferToSession(dto);

        if (chRepos.existsById(dto.getChildId())) {
            var child = chRepos.findById(dto.getChildId()).get();
            if (child.getLocation() != null) {
                session.setChild(child);
            } else {
                throw new BadRequestException("Child has to choose a location before being able to participate in a session.");
            }


        } else {
            throw new RecordNotFoundException("No id found");
        }

        session.setCreationDate(LocalDate.now());

        sRepos.save(session);

        return transferToDto(session);
    }

    public void deleteSession(Long id) {


        Session session = sRepos.findById(id).orElseThrow(() -> new RecordNotFoundException("Session id not found"));

        sRepos.delete(session);

    }


    public static SessionDto transferToDto(Session session) {
        SessionDto dto = new SessionDto();
        dto.setId(session.getId());
        dto.setDate(session.getCreationDate());


        if (session.getChild() != null) {

            dto.setChildName(session.getChild().getName());
            dto.setLocationName(session.getChild().getLocation().getNameLoc());
            dto.setAvailabilityList(session.getChild().getAvailabilityList());
        }
//        if (session.getUser() != null) {
//            dto.setUsername((session.getUser().getUsername()));
//        }


        return dto;

    }

    public Session transferToSession(SessionInputDto inputDto) {
        var session = new Session();


        session.setChild(chService.transferToChild(chService.getChild(inputDto.getChildId())));


//        session.setUser(uService.transferToUser(uService.getUser(inputDto.getUserName())));


        sRepos.save(session);

        inputDto.setId(session.getId());

        return session;
    }


}
