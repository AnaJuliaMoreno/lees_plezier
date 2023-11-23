package com.example.leesplezier.repository;
import com.example.leesplezier.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LocationRepository extends JpaRepository<Location, Long> {
}
