package com.example.shoppingcartapirodrigoroa;

import com.example.shoppingcartapirodrigoroa.repository.Cart;
import com.example.shoppingcartapirodrigoroa.repository.Order;
import com.example.shoppingcartapirodrigoroa.utils.CacheService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;


@SpringBootApplication
public class ShoppingCartApiRodrigoRoaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartApiRodrigoRoaApplication.class, args);

        CacheService.putCache("1", new Cart());
        CacheService.getCache().get("1").setOrdersList(new ArrayList<>());
    }

}
