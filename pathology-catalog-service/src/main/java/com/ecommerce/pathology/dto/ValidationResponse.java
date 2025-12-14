package com.ecommerce.pathology.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationResponse {

    private boolean valid;
    private String reason;
}
