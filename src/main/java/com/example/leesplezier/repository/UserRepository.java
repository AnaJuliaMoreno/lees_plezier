package com.example.leesplezier.repository;

import com.example.leesplezier.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
