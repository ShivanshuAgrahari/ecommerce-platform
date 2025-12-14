package com.ecommerce.cart.client;

import com.ecommerce.cart.domain.Division;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {
    private Division division;
    private String itemId;
    private Integer quantity;

    private String name;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}
