package com.ecommerce.cart.service;

import com.ecommerce.cart.domain.*;
import com.ecommerce.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public Cart getOrCreateCart(String userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> cartRepository.save(
                        Cart.builder()
                                .userId(userId)
                                .status(CartStatus.ACTIVE)
                                .totalAmount(0)
                                .build()
                ));
    }

    public Cart addItem(String userId, CartItem item) {
        Cart cart = getOrCreateCart(userId);

        if (cart.getStatus() != CartStatus.ACTIVE) {
            throw new IllegalStateException("Cart is not active");
        }

        item.setTotalPrice(item.getUnitPrice() * item.getQuantity());
        cart.getItems().add(item);

        recalculate(cart);
        return cartRepository.save(cart);
    }

    private void recalculate(Cart cart) {
        double total = cart.getItems()
                .stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
        cart.setTotalAmount(total);
    }
}
