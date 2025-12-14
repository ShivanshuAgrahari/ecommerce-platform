package com.ecommerce.medicine.controller;

import com.ecommerce.medicine.dto.*;
import com.ecommerce.medicine.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/medicines")
@RequiredArgsConstructor
public class MedicineValidationController {

    private final MedicineRepository repository;

    @PostMapping("/validate")
    public ValidationResponse validate(@RequestBody MedicineValidationRequest request) {

        for (MedicineValidationRequest.MedicineItem item : request.getItems()) {
            var medicine = repository.findByIdAndActiveTrue(item.getItemId());
            if (medicine.isEmpty()) {
                return new ValidationResponse(false, "Medicine inactive or missing");
            }
            // stock check later
        }
        return new ValidationResponse(true, null);
    }
}
