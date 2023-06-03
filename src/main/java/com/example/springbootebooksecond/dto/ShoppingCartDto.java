package com.example.springbootebooksecond.dto;

import com.example.springbootebooksecond.models.BookToShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDto {
    private Long id;
    private String userEmail;
    private List<BookToShoppingCart> bookToShoppingCarts;
}
