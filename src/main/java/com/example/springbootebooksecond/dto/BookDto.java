package com.example.springbootebooksecond.dto;



import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
public class BookDto {

    private Long id;
    @NotEmpty(message = "Book title should not be empty!")
    private String title;
    @NotEmpty(message = "Book author should not be empty!")
    private String author;
    @NotEmpty(message = "Photo link should not be empty!")
    private String photoUrl;
    @NotEmpty(message = "Book content should not be empty!")
    private String content;
    @NotEmpty(message = "Book price should not be empty!")
    private int price;
    private String demoVersion;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
