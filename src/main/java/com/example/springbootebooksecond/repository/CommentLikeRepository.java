package com.example.springbootebooksecond.repository;

import com.example.springbootebooksecond.models.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    int countByUserId(Long userId);
}
