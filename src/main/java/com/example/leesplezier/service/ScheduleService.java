package com.example.leesplezier.service;

import com.example.leesplezier.dto.ScheduleDto;
import com.example.leesplezier.expection.RecordNotFoundException;
import com.example.leesplezier.model.Schedule;
import com.example.leesplezier.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    //dependency injection
    private final ScheduleRepository sRepos;

//    private final LocationRepository locationRepository;
//    private final LocationService locationService;

    //    public WorkingHoursService(WorkingHoursRepository whRepos,
//                               LocationRepository locationRepository,
//                               LocationService locationService) {
//        this.whRepos = whRepos;
//        this.locationRepository = locationRepository;
//        this.locationService = locationService;
//    }
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
//        return transferWHListToDtoList(whList);
//
//
//    }

    }

    public ScheduleDto getSchedule(Long id) {
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

    public void deleteSchedule(Long id) {
        sRepos.deleteById(id);
    }

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

//    public List<Schedule
//   Dto> transferWHListToDtoList(List<WorkingHours> workingHoursList){
//        List<WorkingHoursDto> whDtoList = new ArrayList<>();
//
//        for(WorkingHours wh : workingHoursList) {
//            WorkingHoursDto dto = transferToDto(wh);
//            if(wh.getLocationHours() != null){
//                dto.setLocationDtoHashSet(locationService.tran(wh.getLocationHours()));
//            }
//            whDtoList.add(dto);
//
//        }
//        return whDtoList;
//    }

    // Mapping From Entity to DTO
    //1. Create a new instance of ClassDto called -dto.
    //2. Get the data from the Class object and set it to the -Dto object.
    public static ScheduleDto transferToDto(Schedule schedule) {
        ScheduleDto dto = new ScheduleDto();

        dto.id = schedule.getId();
        dto.setDayOfWeek(schedule.getDayOfWeek());
        dto.setOpensAt(schedule.getOpensAt());
        dto.setClosesAt(schedule.getClosesAt());

        // if dto is public
        //dto.closesAt = schedule.getClosesAt();

//        for(LocationDto dto1: schedule){
//
//        }


//        if(workingHours.getLocationHours() !=null){
//            dto.setLocationDto(LocationService.transferToDto(workingHours.getLocationHours()));
//        }

        return dto;
    }

}
