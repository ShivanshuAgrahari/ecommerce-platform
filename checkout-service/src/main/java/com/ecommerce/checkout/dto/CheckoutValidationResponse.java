package com.ecommerce.checkout.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckoutValidationResponse {
    private boolean valid;
    private String message;
}
