package com.ecommerce.pathology.controller;

import com.ecommerce.pathology.dto.*;
import com.ecommerce.pathology.repository.PathologyTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/pathology-tests")
@RequiredArgsConstructor
public class PathologyValidationController {

    private final PathologyTestRepository repository;

    @PostMapping("/validate")
    public ValidationResponse validate(@RequestBody PathologyValidationRequest request) {

        for (PathologyValidationRequest.PathologyItem item : request.getItems()) {
            if (repository.findByIdAndActiveTrue(item.getItemId()).isEmpty()) {
                return new ValidationResponse(false, "Pathology test inactive or missing");
            }
        }
        return new ValidationResponse(true, null);
    }
}
