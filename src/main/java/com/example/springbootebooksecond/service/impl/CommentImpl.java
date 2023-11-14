package com.example.springbootebooksecond.service.impl;

import com.example.springbootebooksecond.models.Comment;
import com.example.springbootebooksecond.models.UserEntity;
import com.example.springbootebooksecond.repository.BookRepository;
import com.example.springbootebooksecond.repository.CommentRepository;
import com.example.springbootebooksecond.repository.UserRepository;
import com.example.springbootebooksecond.service.CommentService;
import com.example.springbootebooksecond.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final BookRepository bookRepository;

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

    @Override
    public void addLike(Long commentId, String userEmail) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("Comment not found with ID: " + commentId)
        );

        UserEntity user = userRepository.findByEmail(userEmail); // Replace with your actual user retrieval logic

        comment.addLike(user);
        commentRepository.save(comment);
    }

    @Override
    public void removeLike(Long commentId, String userEmail) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("Comment not found with ID: " + commentId)
        );

        UserEntity user = userRepository.findByEmail(userEmail); // Replace with your actual user retrieval logic

        comment.removeLike(user);
        commentRepository.save(comment);
    }







//    @Override
//    public void incrementLikes(Long commentId) {
//        Comment comment = commentRepository.findById(commentId).orElseThrow(
//                () -> new IllegalArgumentException("Comment not found with ID: " + commentId)
//        );
//
//        comment.setLikes(comment.getLikes() + 1);
//        commentRepository.save(comment);
//    }


}