package com.ecommerce.pathology.service;

import com.ecommerce.pathology.domain.PathologyTest;
import com.ecommerce.pathology.dto.CreatePathologyTestRequest;
import com.ecommerce.pathology.repository.PathologyTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PathologyService {

    private final PathologyTestRepository repository;

    public PathologyTest create(CreatePathologyTestRequest request) {
        PathologyTest test = PathologyTest.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .fastingRequired(request.getFastingRequired())
                .active(request.getActive())
                .build();

        return repository.save(test);
    }

    public PathologyTest getActiveById(String id) {
        return repository.findByIdAndActiveTrue(id)
                .orElseThrow(() ->
                        new RuntimeException("Pathology test not found or inactive"));
    }
}
