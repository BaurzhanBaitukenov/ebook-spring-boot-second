package com.example.springbootebooksecond.service;

import com.example.springbootebooksecond.models.Comment;

public interface CommentService {
    Comment addComment(Comment comment, String userEmail);
    void deleteComment(Long commentId);
    void updateComment(Comment comment);
    Comment findCommentById(long id);
    void incrementLikes(Long commentId);
}
