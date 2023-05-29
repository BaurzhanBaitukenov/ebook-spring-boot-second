package com.example.springbootebooksecond.dto;



import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ClubDto {

    private Long id;
    @NotEmpty(message = "Book title should not be empty!")
    private String title;
    @NotEmpty(message = "Book author should not be empty!")
    private String author;
    @NotEmpty(message = "Photo link should not be empty!")
    private String photoUrl;
    @NotEmpty(message = "Book content should not be empty!")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
