package com.example.springbootebooksecond.test;

import com.example.springbootebooksecond.models.Comment;
import com.example.springbootebooksecond.repository.CommentRepository;
import com.example.springbootebooksecond.service.impl.CommentImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CommentImplTest {
    private CommentImpl commentService;

    @Mock
    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        commentService = new CommentImpl(commentRepository);
    }

    @Test
    void testAddComment_ShouldSaveComment() {
        Comment comment = new Comment();
        comment.setContent("Test Comment");
        String userEmail = "test@example.com";
        LocalDateTime now = LocalDateTime.now();
        commentService.addComment(comment, userEmail);

        assertEquals(userEmail, comment.getUserEmail());
        assertEquals(now.getYear(), comment.getCreatedAt().getYear());
        assertEquals(now.getMonth(), comment.getCreatedAt().getMonth());
        assertEquals(now.getDayOfMonth(), comment.getCreatedAt().getDayOfMonth());

        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    void testDeleteComment_ShouldDeleteCommentById() {
        Long commentId = 1L;
        commentService.deleteComment(commentId);

        verify(commentRepository, times(1)).deleteById(commentId);
    }

    @Test
    void testUpdateComment_ShouldSaveUpdatedComment() {
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setContent("Updated Comment");

        commentService.updateComment(comment);

        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    void testFindCommentById_WithExistingComment_ShouldReturnComment() {
        Long commentId = 1L;
        Comment expectedComment = new Comment();
        expectedComment.setId(commentId);
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(expectedComment));

        Comment actualComment = commentService.findCommentById(commentId);

        assertEquals(expectedComment, actualComment);
        verify(commentRepository, times(1)).findById(commentId);
    }
}
