package com.example.springbootebooksecond.service;

import com.example.springbootebooksecond.models.Comment;

public interface CommentService {
    Comment addComment(Comment comment, String userEmail);
}
