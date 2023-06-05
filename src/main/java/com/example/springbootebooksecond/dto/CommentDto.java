package com.example.springbootebooksecond.dto;


import com.example.springbootebooksecond.models.Book;
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
    private String user;
    private String content;
    private LocalDateTime createdAt;
}
