package com.ecommerce.checkout.client;



import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartResponse {

    private String id;
    private String userId;
    private String status;
    private BigDecimal totalAmount;
    private List<CartItemResponse> items;
}
