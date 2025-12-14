package com.ecommerce.cart.domain;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {
    private Division division;
    private String itemId;
    private String name;
    private int quantity;


    private Double unitPrice;

    private Double totalPrice;
}
