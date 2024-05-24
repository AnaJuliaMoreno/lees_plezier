package com.example.leesplezier.services;

import com.example.leesplezier.dtos.SessionDto;
import com.example.leesplezier.exceptions.RecordNotFoundException;
import com.example.leesplezier.models.Location;
import com.example.leesplezier.models.Session;
import com.example.leesplezier.models.User;
import com.example.leesplezier.repositories.LocationRepository;
import com.example.leesplezier.repositories.SessionRepository;
import com.example.leesplezier.repositories.UserRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SessionService {
    private final SessionRepository sRepos;
    private final UserRepository uRepos;
    private final UserService uService;
    private final LocationService locationService;
    private final LocationRepository locationRepository;


    public SessionService(SessionRepository sRepos, UserRepository uRepos, UserService uService, LocationService locationService, LocationRepository locationRepository) {
        this.sRepos = sRepos;
        this.uRepos = uRepos;
        this.uService = uService;
        this.locationService = locationService;
        this.locationRepository = locationRepository;
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

    public SessionDto createSession(SessionDto dto) {

        Session session = transferToSession(dto);
        Session savedSession = sRepos.save(session);

        return transferToDto(savedSession);
    }

    public void deleteSession(Long id) {


        Session session = sRepos.findById(id).orElseThrow(() -> new RecordNotFoundException("Session id not found"));

        sRepos.delete(session);

    }


    public static SessionDto transferToDto(Session session) {
        SessionDto dto = new SessionDto();
        dto.setId(session.getId());
        dto.setSessionDate(session.getSessionDate());
        dto.setStartTime(session.getStartTime());
        dto.setVolunteersName(session.getVolunteersName());
        dto.setComment(session.getComment());

        Location location = session.getLocation();
        if (location != null && location.getName() != null) {
            dto.setLocationName(location.getName());
        }

        User user = session.getUser();
        if (user != null) {
            dto.setUsername(user.getUsername());
        }

        return dto;

    }

    public Session transferToSession(SessionDto sessionDto) {
        var session = new Session();

        session.setSessionDate(sessionDto.getSessionDate());
        session.setVolunteersName(sessionDto.getVolunteersName());
        session.setStartTime(sessionDto.getStartTime());
        session.setComment(sessionDto.getComment());


        String locationName = sessionDto.getLocationName();
        if (locationName != null) {
            Location location = locationRepository.findByNameEqualsIgnoreCase(locationName);
            if (location != null) {
                session.setLocation(location);
            }
        }


        String username = sessionDto.getUsername();
        if (username != null) {
            Optional<User> userOptional = uRepos.findByUsername(username);
          userOptional.ifPresent(session::setUser);
        }

        return session;
    }


}
