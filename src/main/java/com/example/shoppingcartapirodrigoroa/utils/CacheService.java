package com.example.shoppingcartapirodrigoroa.utils;
import com.example.shoppingcartapirodrigoroa.repository.Cart;
import com.example.shoppingcartapirodrigoroa.repository.Order;

import java.util.HashMap;
import java.util.Map;

public class CacheService {
    public static Map < String, Cart> cache = new HashMap < > ();
    public static Map < String, Order> orderCache = new HashMap < > ();

    public static final String UserName = "test";
    public static final int id = 1;

    public static Map < String, Cart > getCache() {
        return cache;
    }
    public static Map < String, Order > getOrderCache() {return orderCache;}

    public static void putCache(String key, Cart cart) {
        cache.put(key, cart);
    }

    public static void putOrderCache(String key, Order order){
        orderCache.put(key, order);
    }

}