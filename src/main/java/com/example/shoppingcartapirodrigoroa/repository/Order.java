package com.example.shoppingcartapirodrigoroa.repository;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    long id;
    Product product;
    double quantity;

    public Order(Product product1, Long quantity) {
    }
}
