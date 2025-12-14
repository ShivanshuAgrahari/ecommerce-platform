package com.ecommerce.checkout.controller;


import com.ecommerce.checkout.client.CartClient;
import com.ecommerce.checkout.client.CartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final CartClient cartClient;

    @PostMapping("/initiate")
    public CartResponse initiateCheckout(
            @RequestHeader("X-USER-ID") String userId
    ) {
        CartResponse cart = cartClient.getCart(userId);

        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        if (!"ACTIVE".equals(cart.getStatus())) {
            throw new IllegalStateException("Cart is not active");
        }


        // Lock the cart
        return cartClient.lockCart(userId);
    }
}
