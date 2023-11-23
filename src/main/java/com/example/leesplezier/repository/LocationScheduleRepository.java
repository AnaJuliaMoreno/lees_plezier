package com.example.leesplezier.repository;

import com.example.leesplezier.model.LocationSchedule;
import com.example.leesplezier.model.LocationScheduleKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationScheduleRepository extends JpaRepository<LocationSchedule, LocationScheduleKey> {
    List<LocationSchedule> findAllByLocationId(Long locationId);
    List<LocationSchedule> findAllByScheduleId(Long scheduleId);
}
