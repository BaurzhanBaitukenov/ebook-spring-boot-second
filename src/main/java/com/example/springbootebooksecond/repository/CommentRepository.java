package com.example.springbootebooksecond.repository;

import com.example.springbootebooksecond.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
