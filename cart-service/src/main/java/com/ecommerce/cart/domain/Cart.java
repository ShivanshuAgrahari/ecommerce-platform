package com.ecommerce.cart.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "carts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {

    @Id
    private String id;

    private String userId;

    @Builder.Default
    private List<CartItem> items = new ArrayList<>();

    private BigDecimal totalAmount;

    private CartStatus status;
}
