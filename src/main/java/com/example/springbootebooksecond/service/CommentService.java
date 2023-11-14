package com.example.springbootebooksecond.service;

import com.example.springbootebooksecond.models.Comment;

public interface CommentService {
    Comment addComment(Comment comment, String userEmail);
    void deleteComment(Long commentId);
    void updateComment(Comment comment);
    Comment findCommentById(long id);
    void addLike(Long commentId, String userEmail);
    void removeLike(Long commentId, String userEmail);
//    void incrementLikes(Long commentId);
}