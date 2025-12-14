package com.ecommerce.checkout.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class MedicineValidationClient {
    private final RestClient restClient =
            RestClient.create("http://localhost:8082");

    public ValidationResponse validate(Object request) {
        return restClient.post()
                .uri("/api/v2/medicines/validate")
                .body(request)
                .retrieve()
                .body(ValidationResponse.class);
    }
}
