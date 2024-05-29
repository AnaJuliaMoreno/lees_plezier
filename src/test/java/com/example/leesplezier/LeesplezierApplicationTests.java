package com.example.leesplezier;

import com.example.leesplezier.dtos.ScheduleDto;
import com.example.leesplezier.exceptions.RecordNotFoundException;
import com.example.leesplezier.models.Schedule;
import com.example.leesplezier.repositories.ScheduleRepository;
import com.example.leesplezier.services.ScheduleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LeesplezierApplicationTests {

    @Mock
    ScheduleRepository scheduleRepository;

    @InjectMocks
    ScheduleService scheduleService;
    @Test
    @DisplayName("Should return all schedules")
    void testGetAllSchedule() {
        // Arrange
        Schedule schedule = new Schedule();
        schedule.setId(1);
        schedule.setDayOfWeek("Monday");
        schedule.setOpensAt(900);
        schedule.setClosesAt(1700);

        List<Schedule> schedules = new ArrayList<>();
        schedules.add(schedule);

        when(scheduleRepository.findAll()).thenReturn(schedules);

        // Act
        List<ScheduleDto> result = scheduleService.getAllSchedule();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Monday", result.get(0).getDayOfWeek());
        assertEquals(900, result.get(0).getOpensAt());
        assertEquals(1700, result.get(0).getClosesAt());
    }

    @Test
    @DisplayName("Should return schedule by ID")
    void testGetScheduleById() {
        // Arrange
        Schedule schedule = new Schedule();
        schedule.setId(1);
        schedule.setDayOfWeek("Monday");
        schedule.setOpensAt(900);
        schedule.setClosesAt(1700);

        when(scheduleRepository.findById(anyInt())).thenReturn(Optional.of(schedule));

        // Act
        ScheduleDto result = scheduleService.getScheduleById(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Monday", result.getDayOfWeek());
        assertEquals(900, result.getOpensAt());
        assertEquals(1700, result.getClosesAt());
    }

    @Test
    @DisplayName("Should throw exception when schedule not found by ID")
    void testGetScheduleById_NotFound() {
        // Arrange
        when(scheduleRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RecordNotFoundException.class, () -> scheduleService.getScheduleById(1));
    }

    @Test
    @DisplayName("Should add new schedule and return DTO")
    void testAddSchedule() {
        // Arrange
        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setDayOfWeek("Monday");
        scheduleDto.setOpensAt(900);
        scheduleDto.setClosesAt(1700);

        Schedule schedule = new Schedule();
        schedule.setDayOfWeek("Monday");
        schedule.setOpensAt(900);
        schedule.setClosesAt(1700);

        Schedule savedSchedule = new Schedule();
        savedSchedule.setId(1);
        savedSchedule.setDayOfWeek("Monday");
        savedSchedule.setOpensAt(900);
        savedSchedule.setClosesAt(1700);

        when(scheduleRepository.save(schedule)).thenReturn(savedSchedule);

        // Act
        ScheduleDto result = scheduleService.addSchedule(scheduleDto);

        // Assert
        assertNotNull(result);
        assertEquals("Monday", result.getDayOfWeek());
        assertEquals(900, result.getOpensAt());
        assertEquals(1700, result.getClosesAt());

    }

    @Test
    @DisplayName("Should update schedule by ID")
    void testUpdateSchedule() {
        // Arrange
        Schedule existingSchedule = new Schedule();
        existingSchedule.setId(1);
        existingSchedule.setDayOfWeek("Monday");
        existingSchedule.setOpensAt(900);
        existingSchedule.setClosesAt(1700);

        ScheduleDto updatedScheduleDto = new ScheduleDto();
        updatedScheduleDto.setId(1);
        updatedScheduleDto.setDayOfWeek("Tuesday");
        updatedScheduleDto.setOpensAt(1000);
        updatedScheduleDto.setClosesAt(1800);

        when(scheduleRepository.findById(anyInt())).thenReturn(Optional.of(existingSchedule));


        // Act
        scheduleService.updateSchedule(1, updatedScheduleDto);

        // Assert

        assertEquals("Tuesday", existingSchedule.getDayOfWeek());
        assertEquals(1000, existingSchedule.getOpensAt());
        assertEquals(1800, existingSchedule.getClosesAt());
    }

    @Test
    @DisplayName("Should throw exception when updating schedule by unknown ID")
    void testUpdateSchedule_NotFound() {
        // Arrange
        ScheduleDto updatedScheduleDto = new ScheduleDto();
        updatedScheduleDto.setId(1);
        updatedScheduleDto.setDayOfWeek("Tuesday");
        updatedScheduleDto.setOpensAt(1000);
        updatedScheduleDto.setClosesAt(1800);

        when(scheduleRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RecordNotFoundException.class, () -> scheduleService.updateSchedule(1, updatedScheduleDto));
    }
}
