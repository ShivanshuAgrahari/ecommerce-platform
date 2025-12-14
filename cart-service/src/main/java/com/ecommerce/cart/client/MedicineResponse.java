package com.ecommerce.cart.client;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MedicineResponse {
    private String id;
    private String name;
    private BigDecimal price;
    private Boolean active;
}
