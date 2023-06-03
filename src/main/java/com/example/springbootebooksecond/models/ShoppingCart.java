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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userEmail;
    @OneToMany(mappedBy = "shoppingCartId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookToShoppingCart> bookToShoppingCarts;
}
