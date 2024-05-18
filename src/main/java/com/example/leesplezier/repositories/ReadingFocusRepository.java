package com.example.leesplezier.repositories;

import com.example.leesplezier.models.ReadingFocus;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReadingFocusRepository extends JpaRepository<ReadingFocus, String> {
ReadingFocus findByNameContainingIgnoreCase(String name);
}
