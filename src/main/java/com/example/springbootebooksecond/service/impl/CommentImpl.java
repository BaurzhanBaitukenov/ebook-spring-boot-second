package com.example.springbootebooksecond.service.impl;

import com.example.springbootebooksecond.models.Comment;
import com.example.springbootebooksecond.repository.CommentRepository;
import com.example.springbootebooksecond.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CommentImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Comment addComment(Comment comment, String userEmail) {
        comment.setUserEmail(userEmail);
        comment.setCreatedAt(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public void updateComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Comment findCommentById(long id) {
        return commentRepository.findById(id).get();
    }


}
