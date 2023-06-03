package com.example.springbootebooksecond.dto;

import com.example.springbootebooksecond.models.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookToShoppingCartDto {
    private Long id;
    private Long shoppingCartId;
    private Book book;
    private int amount;
}
