package com.ecommerce.pathology.dto;

import lombok.Data;
import java.util.List;

@Data
public class PathologyValidationRequest {

    private List<PathologyItem> items;

    @Data
    public static class PathologyItem {
        private String itemId;
        private Integer quantity;
    }
}
