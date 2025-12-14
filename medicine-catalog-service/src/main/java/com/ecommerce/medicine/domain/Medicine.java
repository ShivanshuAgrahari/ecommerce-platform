package com.ecommerce.medicine.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "medicines")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medicine {

    @Id
    private String id;

    private String name;
    private String description;

    private BigDecimal price;
    private Integer stock;

    private Boolean prescriptionRequired;
    private Boolean active;
}
