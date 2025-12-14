package com.ecommerce.checkout.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CartClient {

    private final RestClient restClient =
            RestClient.create("http://localhost:8081");

    public CartResponse getCart(String userId) {
        return restClient.get()
                .uri("/api/cart")
                .header("X-USER-ID", userId)
                .retrieve()
                .body(CartResponse.class);
    }

    public CartResponse lockCart(String userId) {
        return restClient.put()
                .uri("/api/cart/lock")
                .header("X-USER-ID", userId)
                .retrieve()
                .body(CartResponse.class);
    }
}
