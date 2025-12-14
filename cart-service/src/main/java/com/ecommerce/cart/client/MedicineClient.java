package com.ecommerce.cart.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class MedicineClient {

    private final RestClient restClient = RestClient.create("http://localhost:8082");

    public MedicineResponse getMedicineById(String medicineId) {
        return restClient.get()
                .uri("/api/v2/medicines/{id}", medicineId)
                .retrieve()
                .body(MedicineResponse.class);
    }
}
