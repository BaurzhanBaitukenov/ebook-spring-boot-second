package com.example.springbootebooksecond.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {
    @Id
    private Long id;
    private Long userId;
    @OneToMany(mappedBy = "shoppingCartId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookToShoppingCart> bookToShoppingCarts;
}
