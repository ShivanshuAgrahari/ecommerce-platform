package com.ecommerce.cart.controller;

import com.ecommerce.cart.domain.Cart;
import com.ecommerce.cart.domain.CartItem;
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
        CartItem item = CartItem.builder()
                .division(request.getDivision())
                .itemId(request.getItemId())
                .name(request.getName())
                .quantity(request.getQuantity())
                .unitPrice(request.getUnitPrice())
                .totalPrice(request.getUnitPrice() * request.getQuantity())
                .build();

        return cartService.addItem(userId, item);
    }
}
