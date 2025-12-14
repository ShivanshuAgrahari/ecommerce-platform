package com.ecommerce.checkout.controller;


import com.ecommerce.checkout.client.CartClient;
import com.ecommerce.checkout.client.CartResponse;
import com.ecommerce.checkout.client.MedicineValidationClient;
import com.ecommerce.checkout.client.PathologyValidationClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final CartClient cartClient;
    private final MedicineValidationClient medicineValidationClient;
    private final PathologyValidationClient pathologyValidationClient;

    @PostMapping("/initiate")
    public CartResponse initiateCheckout(
            @RequestHeader("X-USER-ID") String userId
    ) {
        CartResponse cart = cartClient.getCart(userId);

        if (!"ACTIVE".equals(cart.getStatus())) {
            throw new IllegalStateException("Cart not active");
        }

        // 1. Split items by division
        var medicineItems = cart.getItems().stream()
                .filter(i -> "MEDICINE".equals(i.getDivision()))
                .toList();

        var pathologyItems = cart.getItems().stream()
                .filter(i -> "PATHOLOGY".equals(i.getDivision()))
                .toList();

        // 2. Validate medicine
        if (!medicineItems.isEmpty()) {
            var response = medicineValidationClient.validate(
                    Map.of("items", medicineItems));
            if (!response.isValid()) {
                throw new IllegalStateException(response.getReason());
            }
        }

        // 3. Validate pathology
        if (!pathologyItems.isEmpty()) {
            var response = pathologyValidationClient.validate(
                    Map.of("items", pathologyItems));
            if (!response.isValid()) {
                throw new IllegalStateException(response.getReason());
            }
        }

        // 4. Lock cart ONLY after all validations pass
        return cartClient.lockCart(userId);
    }

}
