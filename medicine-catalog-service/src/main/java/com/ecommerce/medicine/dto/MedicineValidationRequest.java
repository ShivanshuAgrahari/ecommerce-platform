package com.ecommerce.medicine.dto;
import lombok.Data;
import java.util.List;

@Data
public class MedicineValidationRequest {
    private List<MedicineItem> items;

    @Data
    public static class MedicineItem {
        private String itemId;
        private Integer quantity;
    }
}
