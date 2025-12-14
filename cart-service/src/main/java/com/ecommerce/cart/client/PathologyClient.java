package com.ecommerce.cart.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PathologyClient {
    private final RestClient restClient =
            RestClient.create("http://localhost:8083");

    public PathologyResponse getTestById(String testId) {
        return restClient.get()
                .uri("/api/v2/pathology-tests/{id}", testId)
                .retrieve()
                .body(PathologyResponse.class);
    }
}
