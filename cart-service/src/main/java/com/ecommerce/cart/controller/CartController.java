package com.ecommerce.cart.controller;

import com.ecommerce.cart.domain.Cart;
import com.ecommerce.cart.dto.AddCartItemRequest;
import com.ecommerce.cart.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public Cart getCart(@RequestHeader("X-USER-ID") String userId) {
        return cartService.getOrCreateCart(userId);
    }

    @PostMapping("/add")
    public Cart addItem(
            @RequestHeader("X-USER-ID") String userId,
            @Valid @RequestBody AddCartItemRequest request
    ) {
        return cartService.addItem(userId, request);
    }

    @PutMapping("/lock")
    public Cart lockCart(@RequestHeader("X-USER-ID") String userId) {
        return cartService.lockCart(userId);
    }

}
