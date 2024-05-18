package com.example.leesplezier.repositories;

import com.example.leesplezier.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainsIgnoreCase(String title);

    List<Book> findByBookSubjectsContainsIgnoreCase(String subject);

    List<Book> findByTargetAge(int targetAge);
    List<Book> findByAuthorContainsIgnoreCase(String subject);
}
