package com.example.springbootebooksecond.dto;


import com.example.springbootebooksecond.models.Book;
import com.example.springbootebooksecond.models.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentDto {

    private Long id;
    private Book book;
    private UserEntity user;
    private String content;
    private LocalDateTime createdAt;
}
