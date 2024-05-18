package com.example.leesplezier.repositories;
import com.example.leesplezier.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LocationRepository extends JpaRepository<Location, Integer> {
Location findByNameLocEqualsIgnoreCase(String nameLoc);
}
