package com.example.shoppingcartapirodrigoroa.controllers;

import com.example.shoppingcartapirodrigoroa.configurations.TaxCategory;
import com.example.shoppingcartapirodrigoroa.interfaces.ProductInterface;
import com.example.shoppingcartapirodrigoroa.repository.Order;
import com.example.shoppingcartapirodrigoroa.utils.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class RestControllers {

    @Autowired
    ProductInterface productInterface ;



    @GetMapping("/getCartDetails")
    public ResponseEntity<String> getCartDetails(){
        if (CacheService.getCache().get("1") == null || CacheService.getCache().get("1").getOrdersList().size() == 0) {
            return ResponseEntity.badRequest()
                    .body("Your cart is empty");
        }
        calculateTotals();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CacheService.getCache().get("1").toString());
    }

    @PostMapping("/addProductToCart")
    public ResponseEntity<String> addProductToCart(
            @RequestParam(name = "productId") Long productId,
            @RequestParam(name = "quantity") Long quantity
            ){
        AtomicReference<String> response = new AtomicReference<>();
        AtomicBoolean productExistInCart = new AtomicBoolean(false);
        AtomicReference<Integer> orderId = new AtomicReference<>();
        if (CacheService.getCache().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("There are no active carts");
        }

        productInterface.findById(productId).ifPresentOrElse(product1 -> {

            if(CacheService.getCache().get("1") == null || CacheService.getCache().get("1").getOrdersList().isEmpty()){
                Order order = new Order();
                order.setProduct(product1);
                ArrayList<Order> orderList = new ArrayList<>();
                orderList.add(order);
                CacheService.getCache().get("1").setOrdersList(orderList);
            }
            for (int i = 0; i < CacheService.getCache().get("1").getOrdersList().size(); i++) {
                if(CacheService.getCache().get("1").getOrdersList().get(i).getProduct().getId() == productId){
                    productExistInCart.set(true);
                     orderId.set(i);
                }
            }
            if(productExistInCart.get() == false){
                Order order = new Order(CacheService.getCache().get("1").getOrdersList().size()+1, product1, quantity);
                CacheService.getCache().get("1").getOrdersList().add(order);
                orderId.set((int) order.getId());
            }

            if(CacheService.getCache().get("1").getOrdersList().get(orderId.get()).getQuantity() + quantity < 0){
                CacheService.getCache().get("1").getOrdersList().remove(orderId.get());
                response.set("Your product has been removed from the cart");
            }else{
                CacheService.getCache().get("1").getOrdersList().get(orderId.get()).setQuantity(
                        CacheService.getCache().get("1").getOrdersList().get(orderId.get()).getQuantity() + quantity);
            }
            response.set(CacheService.getCache().toString());
            productExistInCart.set(false);
        },
        () -> {
            response.set("The product you are trying to add doesn't exists");
        }
        );

        return ResponseEntity.status(HttpStatus.OK)
                .body(response.get());
    }

    @DeleteMapping("/deleteProductFromCart")
    public ResponseEntity<String> deleteProductFromCart(
            @RequestParam(name = "productId") long productId
    ){
        if (CacheService.getCache().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("There are no active carts");
        }

        for (int i = 0; i < CacheService.getCache().get("1").getOrdersList().size(); i++) {
            if (CacheService.getCache().get("1").getOrdersList().get(i).getProduct().getId() == productId){
                CacheService.getCache().get("1").getOrdersList().remove(CacheService.getCache().get("1").getOrdersList().get(i));
            }
        }


        return ResponseEntity.status(HttpStatus.OK)
                .body(CacheService.getCache().toString());
    }



    @GetMapping("/endCart")
    public ResponseEntity<String> endCart(@RequestParam String id){
        calculateTotals();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CacheService.getCache().get(id).toString());
    }

    private void calculateTotals(){
        double amount = 0;
        double taxes = 0;
        double total = 0;

        for (int i = 0; i < CacheService.getCache().get("1").getOrdersList().size(); i++) {
            amount = amount + CacheService.getCache().get("1").getOrdersList().get(i).getProduct().getPrice();

            if(CacheService.getCache().get("1").getOrdersList().get(i).getProduct().getTaxCategory().equals(TaxCategory.GNRL)){
                taxes = taxes + (CacheService.getCache().get("1").getOrdersList().get(i).getProduct().getPrice() * 0.0875);
            }
            if(CacheService.getCache().get("1").getOrdersList().get(i).getProduct().getTaxCategory().equals(TaxCategory.TIERED)){
                if(CacheService.getCache().get("1").getOrdersList().get(i).getProduct().getPrice() <= 10000.00){
                    taxes = taxes + (CacheService.getCache().get("1").getOrdersList().get(i).getProduct().getPrice() * 0.0875);
                }else {
                    double helper = (CacheService.getCache().get("1").getOrdersList().get(i).getProduct().getPrice() - 10000.00) * 0.015;
                    taxes = taxes + 875.00 + helper;
                }
            }
            if(CacheService.getCache().get("1").getOrdersList().get(i).getProduct().getTaxCategory().equals(TaxCategory.LUXURY)){
                taxes = taxes + (CacheService.getCache().get("1").getOrdersList().get(i).getProduct().getPrice() * 0.015);
            }
        }
        total = amount + taxes;
        CacheService.getCache().get("1").setAmount(amount);
        CacheService.getCache().get("1").setTaxes(taxes);
        CacheService.getCache().get("1").setTotal(total);
    }


}
