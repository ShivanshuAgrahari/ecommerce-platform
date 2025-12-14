package com.ecommerce.medicine.controller;

import com.ecommerce.medicine.domain.Medicine;
import com.ecommerce.medicine.dto.CreateMedicineRequest;
import com.ecommerce.medicine.service.MedicineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    // POST – create medicine (admin/internal)
    @PostMapping
    public Medicine create(
            @Valid @RequestBody CreateMedicineRequest request
    ) {
        return medicineService.create(request);
    }

    // GET – fetch medicine for cart/checkout
    @GetMapping("/{id}")
    public Medicine getById(@PathVariable String id) {
        return medicineService.getActiveById(id);
    }
}
