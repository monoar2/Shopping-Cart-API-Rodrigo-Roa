package com.example.shoppingcartapirodrigoroa.repository;

import com.example.shoppingcartapirodrigoroa.configurations.ProductCategory;
import com.example.shoppingcartapirodrigoroa.configurations.TaxCategory;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product")
@ToString
public class Product {
    @Id
    long id;
    @Column(name = "name")
    String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "productCategory")
    ProductCategory productCategory;
    @Enumerated(EnumType.STRING)
    @Column(name = "taxCategory")
    TaxCategory  taxCategory;
    @Column(name = "price")
    long price;
}
