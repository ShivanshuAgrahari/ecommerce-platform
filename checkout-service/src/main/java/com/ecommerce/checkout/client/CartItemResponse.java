package com.ecommerce.checkout.client;

import lombok.Data;

@Data
public class CartItemResponse {

    private String division;
    private String itemId;
    private Integer quantity;
}
