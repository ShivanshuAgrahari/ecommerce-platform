package com.ecommerce.pathology.controller;

import com.ecommerce.pathology.domain.PathologyTest;
import com.ecommerce.pathology.dto.CreatePathologyTestRequest;
import com.ecommerce.pathology.service.PathologyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/pathology-tests")
@RequiredArgsConstructor
public class PathologyController {

    @PostMapping
    public PathologyTest create(
            @Valid @RequestBody CreatePathologyTestRequest request
    ) {
        return pathologyService.create(request);
    }

    @GetMapping("/{id}")
    public PathologyTest getById(@PathVariable String id) {
        return pathologyService.getActiveById(id);
    }

    private final PathologyService pathologyService;
}
