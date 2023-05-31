package com.example.springbootebooksecond.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookToShoppingCart {
    @Id
    private Long id;
    private Long shoppingCartId;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    private int amount;
}
