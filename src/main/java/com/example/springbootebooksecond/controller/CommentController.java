package com.example.springbootebooksecond.controller;

import com.example.springbootebooksecond.dto.BookDto;
import com.example.springbootebooksecond.models.Book;
import com.example.springbootebooksecond.models.Comment;
import com.example.springbootebooksecond.service.BookService;
import com.example.springbootebooksecond.service.CommentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/clubs/{bookId}/comments")
public class CommentController {

    private final BookService bookService;
    private final CommentService commentService;

    @GetMapping
    public String getAllCommentsForBook(@PathVariable("bookId") Long bookId, Model model) {
        List<Comment> comments = bookService.getCommentsByBookId(bookId);
        model.addAttribute("comments", comments);
        return "comments/comment-list";
    }

    @GetMapping("/new")
    public String showCommentForm(@PathVariable("bookId") Long bookId, Model model) {
        Comment comment = new Comment();
        model.addAttribute("comment", comment);
        model.addAttribute("bookId", bookId);
        return "comments/comment-form";
    }

    @PostMapping("/new")
    public String addComment(@PathVariable("bookId") Long bookId,
                             @Valid @ModelAttribute("comment") Comment comment,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "comments/comment-form";
        }

        Book book = bookService.findBookModelById(bookId);
        comment.setBook(book);
        comment.setCreatedAt(LocalDateTime.now());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        commentService.addComment(comment, username);

        redirectAttributes.addFlashAttribute("successMessage", "Comment added successfully");
        return "redirect:/clubs/{bookId}";
    }


    @GetMapping("/{commentId}/delete")
    public String deleteComment(@PathVariable("bookId") Long bookId,
                                @PathVariable("commentId") Long commentId,
                                RedirectAttributes redirectAttributes) {
        commentService.deleteComment(commentId);
        redirectAttributes.addFlashAttribute("successMessage", "Comment deleted successfully");
        return "redirect:/clubs/" + bookId;
    }


    @GetMapping("/{commentId}/update")
    public String updateCommentPage(@PathVariable("bookId") Long bookId,
                                @PathVariable("commentId") Long commentId,
                                Model model) {
        BookDto book = bookService.findBookById(bookId);
        Comment comment = commentService.findCommentById(commentId);

        model.addAttribute("comment", comment);
        model.addAttribute("book", book);
        return "comments/comment-edit";
    }

    @PostMapping("/{commentId}/update")
    public String updateComment(@PathVariable("bookId") Long bookId,
                                @PathVariable("commentId") Long commentId,
                                @ModelAttribute("comment") Comment comment) {
        Comment existingComment = commentService.findCommentById(commentId);
        comment.setBook(existingComment.getBook()); // Set the book from the existing comment
        comment.setUserEmail(existingComment.getUserEmail());
        commentService.updateComment(comment);
        return "redirect:/clubs/" + bookId;
    }

    @PostMapping("/{commentId}/likes")
    public void updateComment(@PathVariable("commentId") Long commentId) {
        commentService.incrementLikes(commentId);
    }


}
