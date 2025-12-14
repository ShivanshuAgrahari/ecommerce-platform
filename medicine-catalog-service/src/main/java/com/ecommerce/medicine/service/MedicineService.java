package com.ecommerce.medicine.service;

import com.ecommerce.medicine.domain.Medicine;
import com.ecommerce.medicine.dto.CreateMedicineRequest;
import com.ecommerce.medicine.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicineService {
    private final MedicineRepository medicineRepository;

    public Medicine create(CreateMedicineRequest request) {
        Medicine medicine = Medicine.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .prescriptionRequired(request.getPrescriptionRequired())
                .active(request.getActive())
                .build();

        return medicineRepository.save(medicine);
    }

    public Medicine getActiveById(String id) {
        return medicineRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found or inactive"));
    }
}
