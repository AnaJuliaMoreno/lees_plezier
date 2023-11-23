package com.example.leesplezier.repository;

import com.example.leesplezier.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
