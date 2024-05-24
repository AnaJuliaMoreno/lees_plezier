package com.example.leesplezier.services;

import com.example.leesplezier.dtos.LocationDto;
import com.example.leesplezier.exceptions.RecordNotFoundException;
import com.example.leesplezier.models.Location;
import com.example.leesplezier.models.Schedule;
import com.example.leesplezier.repositories.LocationRepository;
import com.example.leesplezier.repositories.ScheduleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

    @Mock
    LocationRepository lRepos;

    @Mock
    ScheduleRepository sRepos;

    @InjectMocks
    LocationService service;

    @Test
    void getAllLoc() {
        //arrange
        Location location1 = new Location(1, "Zuilen", "Picodreef 5", new HashSet<>(), new HashSet<>());
        Location location2 = new Location(2, "Hooggraven", "Concordia 4", new HashSet<>(), new HashSet<>());
        location2.setId(2);

        List<Location> locations = Arrays.asList(location1, location2);

        when(lRepos.findAll()).thenReturn(locations);
        // act
        List<LocationDto> locationDtoList = service.getAllLoc();
        //assert
        assertEquals(2, locationDtoList.size());

        LocationDto locationDto1 = locationDtoList.get(0);
        assertEquals(1, locationDto1.getId());
        assertEquals("Zuilen", locationDto1.getName());
        assertEquals("Picodreef 5", locationDto1.getAddressLoc());

        LocationDto locationDto2 = locationDtoList.get(1);
        assertEquals(2, locationDto2.getId());
        assertEquals("Hooggraven", locationDto2.getName());
        assertEquals("Concordia 4", locationDto2.getAddressLoc());

    }

    @Test
    @DisplayName("Creates location and returns locationDto")
    void getLocationById() {
        //arrange
        Location location = new Location(11, "Zuilen", "Picodreef 5", new HashSet<>(), new HashSet<>());
        location.setId(11);
        when(lRepos.findById(anyInt())).thenReturn(Optional.of(location));

        // act
        LocationDto ldto = service.getLocationById(11);
        //assert
        assertEquals("Zuilen", ldto.getName());
        assertEquals("Picodreef 5", ldto.getAddressLoc());
    }

    @Test
    @DisplayName("Should create a new location and return the given dto")
    void addLoc() {
        // Arrange

        LocationDto locationDto = new LocationDto("Zuilen", "Picodreef 5");
        Location location = new Location(11, "Zuilen", "Picodreef 5", new HashSet<>(), new HashSet<>());
        when(lRepos.save(any(Location.class))).thenReturn(location);

        // Act
        LocationDto addedLocationDto = service.addLoc(locationDto);

        // Assert
        assertEquals("Zuilen", addedLocationDto.getName());
        assertEquals("Picodreef 5", addedLocationDto.getAddressLoc());

    }

    @Test
    @DisplayName("Assigning a schedule to a unknown location should return an error.")
    void assignOneScheduleToLocation() {
        //Arrange
        int unknownLoc = 999;
        int idSchedule = 1;
        when(lRepos.findById(unknownLoc)).thenReturn(Optional.empty());
        when(sRepos.findById(idSchedule)).thenReturn(Optional.of(new Schedule()));

        //Act and assert
        assertThrows(RecordNotFoundException.class, () -> service.assignOneScheduleToLocation(unknownLoc, idSchedule));
    }

    @Test
    @DisplayName("Assigning an unknown schedule to a location should return an error.")
    void assignScheduleToLocation() {
        //Arrange
        int locationId = 1;
        int unknownScheduleId = 999;
        when(lRepos.findById(locationId)).thenReturn(Optional.of(new Location()));
        when(sRepos.findById(unknownScheduleId)).thenReturn(Optional.empty());

        //Act and assert
        assertThrows(RecordNotFoundException.class, () -> service.assignOneScheduleToLocation(locationId, unknownScheduleId));
    }


    @Test
    void deleteLocation() {
        // Arrange
        int locationIdToDelete = 1;

        // Act
        service.deleteLocation(locationIdToDelete);

        // Assert
        verify(lRepos, times(1)).deleteById(locationIdToDelete);
    }

    @Test
    @DisplayName("Should delete schedule from location")
    void deleteScheduleIdByLocationId() {
        // Arrange
        int locationId = 1;
        int scheduleIdToDelete = 10;

        Location location = new Location();
        location.setId(locationId);

        Schedule scheduleToDelete = new Schedule();
        scheduleToDelete.setId(scheduleIdToDelete);

        Set<Schedule> locationSchedules = new HashSet<>();
        locationSchedules.add(scheduleToDelete);
        location.setLocationSchedules(locationSchedules);

        when(lRepos.findById(locationId)).thenReturn(Optional.of(location));
        when(lRepos.save(location)).thenReturn(location);

        // Act
        service.deleteScheduleIdByLocationId(locationId, scheduleIdToDelete);

        // Assert
        verify(lRepos, times(1)).save(location);
        assertFalse(location.getLocationSchedules().contains(scheduleToDelete));
    }

    @Test
    @DisplayName("Should throw RecordNotFoundException for unknown schedule in location")
    void deleteUnknownScheduleIdByLocationId(){
        // Arrange
        int locationId = 1;
        int scheduleUnknown = 999;
        Location location = new Location();
        location.setId(locationId);
        when(lRepos.findById(locationId)).thenReturn(Optional.of(location));

        assertThrows(RecordNotFoundException.class, () -> service.deleteScheduleIdByLocationId(locationId, scheduleUnknown));

    }

    @Test
    @DisplayName("Should throw RecordNotFoundException for unknown location")
    public void deleteScheduleIdByLocationIdUnknown() {
        // Arrange
        int unknownLocationId = 999;
        int scheduleIdToDelete = 10;

        when(lRepos.findById(unknownLocationId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> service.deleteScheduleIdByLocationId(unknownLocationId, scheduleIdToDelete));
    }
}