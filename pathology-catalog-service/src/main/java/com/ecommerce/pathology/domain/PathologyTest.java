package com.ecommerce.pathology.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "pathology_tests")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PathologyTest {

    @Id
    private String id;

    private String name;
    private String description;

    private BigDecimal price;

    private Boolean fastingRequired;
    private Boolean active;
}
