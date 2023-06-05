package com.example.springbootebooksecond.service;

import com.example.springbootebooksecond.models.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();
    Comment getCommentById(Long id);
    Comment addComment(Comment comment);
    Comment updateComment(Comment comment);
    void deleteComment(Long id);
}
