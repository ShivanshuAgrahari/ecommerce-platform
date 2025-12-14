package com.ecommerce.checkout.client;

import lombok.Data;

@Data
public class ValidationResponse {

    private boolean valid;
    private String reason;
}
