package com.example.springbootebooksecond.repository;

import com.example.springbootebooksecond.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT SUM(c.likes) FROM Comment c WHERE c.userEmail = :userEmail")
    Integer countLikesForUser(@Param("userEmail") String userEmail);

}
