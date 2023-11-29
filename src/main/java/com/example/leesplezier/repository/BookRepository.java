package com.example.leesplezier.repository;

import com.example.leesplezier.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
