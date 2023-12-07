package com.example.leesplezier.repository;
import com.example.leesplezier.model.Location;
import com.example.leesplezier.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LocationRepository extends JpaRepository<Location, Long> {

}
