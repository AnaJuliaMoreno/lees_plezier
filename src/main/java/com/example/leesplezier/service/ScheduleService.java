package com.example.leesplezier.service;

import com.example.leesplezier.dto.ScheduleDto;
import com.example.leesplezier.expection.RecordNotFoundException;
import com.example.leesplezier.model.Schedule;
import com.example.leesplezier.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScheduleService {

    //dependency injection
    private static ScheduleRepository sRepos;

    public ScheduleService(ScheduleRepository sRepos) {
        this.sRepos = sRepos;
    }

    //Methods
    public List<ScheduleDto> getAllSchedule() {
        List<Schedule> sList = sRepos.findAll();

        List<ScheduleDto> dtos = new ArrayList<>();
        for (Schedule schedule : sList) {
            dtos.add(transferToDto(schedule));
        }
        return dtos;

    }

    public ScheduleDto getScheduleById(Long id) {
        Optional<Schedule> schedule = sRepos.findById(id);
        if (schedule.isPresent()) {
            ScheduleDto scheduleDto = transferToDto(schedule.get());
            return scheduleDto;
        } else {
            throw new RecordNotFoundException("No working hours found");
        }
    }

    public ScheduleDto addSchedule(ScheduleDto sDto) {
        Schedule schedule = transferToSchedule(sDto);
        sRepos.save(schedule);

        return transferToDto(schedule);
    }
    //Deleting a schedule is not necessary, besides it is not possible to do so if it has already been paired to a Location.
//    public void deleteSchedule(Long id) {
//        sRepos.deleteById(id);
//    }

    public void updateSchedule(Long id, ScheduleDto whDto) {

        Schedule updatedWH = sRepos.findById(id).orElseThrow(() -> new RecordNotFoundException("\"No opening hours found, try a different Id."));
        updatedWH.setId(whDto.getId());
        updatedWH.setDayOfWeek(whDto.getDayOfWeek());
        updatedWH.setOpensAt(whDto.getOpensAt());
        updatedWH.setClosesAt(whDto.getClosesAt());
        sRepos.save(updatedWH);
        // whDto.id = updatedWH.getId();
    }

    //Mapping from Dto to Entity -id is a @GeneratedValue.
    public Schedule transferToSchedule(ScheduleDto scheduleDto) {
        Schedule schedule = new Schedule();

        schedule.setDayOfWeek(scheduleDto.getDayOfWeek());
        schedule.setOpensAt(scheduleDto.getOpensAt());
        schedule.setClosesAt(scheduleDto.getClosesAt());
        sRepos.save(schedule);
        scheduleDto.id = schedule.getId();

        return schedule;
    }

    // Mapping From Entity to DTO
    //1. Create a new instance of ClassDto called -dto.
    //2. Get the data from the Class object and set it to the -Dto object.
    public ScheduleDto transferToDto(Schedule schedule) {
        ScheduleDto dto = new ScheduleDto();

        dto.id = schedule.getId();
        dto.setDayOfWeek(schedule.getDayOfWeek());
        dto.setOpensAt(schedule.getOpensAt());
        dto.setClosesAt(schedule.getClosesAt());


        return dto;
    }

    public Set<Schedule> transferToEntityList(Set<ScheduleDto> sDtoList){
        Set<Schedule> scheduleList = new HashSet<>();

        for(ScheduleDto dto: sDtoList){
            Schedule schedule = transferToSchedule(dto);

            schedule.setOpensAt(dto.getOpensAt());
            schedule.setClosesAt(dto.getClosesAt());
            schedule.setDayOfWeek(dto.getDayOfWeek());
            scheduleList.add(schedule);
            dto.id = schedule.getId();
        }
        return scheduleList;
    }
    public Set<ScheduleDto> transferScheduleListToDto (Set<Schedule> schedules){
        Set<ScheduleDto> sDtoSet =new HashSet<>();
        for(Schedule s: schedules){
            ScheduleDto sDto = transferToDto(s);
            sDtoSet.add(sDto);
        }
        return sDtoSet;
    }

}
