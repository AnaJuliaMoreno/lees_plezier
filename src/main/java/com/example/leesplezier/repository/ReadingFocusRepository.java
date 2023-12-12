package com.example.leesplezier.repository;

import com.example.leesplezier.model.ReadingFocus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingFocusRepository extends JpaRepository<ReadingFocus, String> {
List<ReadingFocus> findByNameContainingIgnoreCase (String name);
}
