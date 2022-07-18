package com.example.shoppingcartapirodrigoroa.interfaces;

import com.example.shoppingcartapirodrigoroa.repository.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInterface extends JpaRepository<Product, Long> {

}
