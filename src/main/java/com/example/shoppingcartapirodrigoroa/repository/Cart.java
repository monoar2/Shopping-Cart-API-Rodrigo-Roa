package com.example.shoppingcartapirodrigoroa.repository;

import lombok.*;

import java.lang.reflect.Array;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    long id;
    ArrayList<Order> ordersList;
    double amount;
    double taxes;
    double total;

    public Cart(Order order) {
        this.ordersList.add(order);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", ordersList=" + ordersList.toString() +
                ", amount=" + amount +
                ", taxes=" + taxes +
                ", total=" + total +
                '}';
    }
}
