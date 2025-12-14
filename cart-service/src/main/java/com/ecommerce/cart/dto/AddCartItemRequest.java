package com.ecommerce.cart.dto;

import com.ecommerce.cart.domain.Division;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddCartItemRequest {
    @NotNull
    private Division division;

    @NotNull
    private String itemId;

    @NotNull
    private String name;

    @NotNull
    @Min(1)
    private Integer quantity;

    @NotNull
    private Double unitPrice;
}
