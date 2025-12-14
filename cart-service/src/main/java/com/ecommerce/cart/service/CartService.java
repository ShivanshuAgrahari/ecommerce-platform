package com.ecommerce.cart.service;

import com.ecommerce.cart.client.MedicineClient;
import com.ecommerce.cart.client.MedicineResponse;
import com.ecommerce.cart.client.PathologyClient;
import com.ecommerce.cart.client.PathologyResponse;
import com.ecommerce.cart.domain.*;
import com.ecommerce.cart.dto.AddCartItemRequest;
import com.ecommerce.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final MedicineClient medicineClient;
    private final PathologyClient pathologyClient;

    public Cart getOrCreateCart(String userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> cartRepository.save(
                        Cart.builder()
                                .userId(userId)
                                .status(CartStatus.ACTIVE)
                                .totalAmount(BigDecimal.ZERO)
                                .build()
                ));
    }

    public Cart addItem(String userId, AddCartItemRequest request) {

        Cart cart = getOrCreateCart(userId);

        if (cart.getStatus() != CartStatus.ACTIVE) {
            throw new IllegalStateException("Cart is not active");
        }

        return switch (request.getDivision()) {
            case MEDICINE -> addMedicine(cart, request);
            case PATHOLOGY -> addPathology(cart, request);
        };
    }

    /* ---------------- MEDICINE ---------------- */

    private Cart addMedicine(Cart cart, AddCartItemRequest request) {

        MedicineResponse medicine =
                medicineClient.getMedicineById(request.getItemId());

        if (medicine == null || Boolean.FALSE.equals(medicine.getActive())) {
            throw new IllegalStateException("Medicine not available");
        }

        BigDecimal unitPrice = medicine.getPrice();
        BigDecimal totalPrice =
                unitPrice.multiply(BigDecimal.valueOf(request.getQuantity()));

        CartItem item = CartItem.builder()
                .division(Division.MEDICINE)
                .itemId(medicine.getId())
                .name(medicine.getName())
                .quantity(request.getQuantity())
                .unitPrice(unitPrice)
                .totalPrice(totalPrice)
                .build();

        cart.getItems().add(item);
        recalculate(cart);

        return cartRepository.save(cart);
    }

    /* ---------------- PATHOLOGY ---------------- */

    private Cart addPathology(Cart cart, AddCartItemRequest request) {

        PathologyResponse test =
                pathologyClient.getTestById(request.getItemId());

        if (test == null || Boolean.FALSE.equals(test.getActive())) {
            throw new IllegalStateException("Pathology test not available");
        }

        BigDecimal unitPrice = test.getPrice();
        BigDecimal totalPrice =
                unitPrice.multiply(BigDecimal.valueOf(request.getQuantity()));

        CartItem item = CartItem.builder()
                .division(Division.PATHOLOGY)
                .itemId(test.getId())
                .name(test.getName())
                .quantity(request.getQuantity())
                .unitPrice(unitPrice)
                .totalPrice(totalPrice)
                .build();

        cart.getItems().add(item);
        recalculate(cart);

        return cartRepository.save(cart);
    }

    private void recalculate(Cart cart) {
        BigDecimal total = cart.getItems().stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        cart.setTotalAmount(total);
    }

    public Cart lockCart(String userId) {
        Cart cart = getOrCreateCart(userId);

        if (cart.getStatus() != CartStatus.ACTIVE) {
            throw new IllegalStateException("Cart cannot be locked");
        }

        cart.setStatus(CartStatus.LOCKED);
        return cartRepository.save(cart);
    }


}
