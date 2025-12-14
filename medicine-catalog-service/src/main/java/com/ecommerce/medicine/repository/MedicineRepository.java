package com.ecommerce.medicine.repository;

import com.ecommerce.medicine.domain.Medicine;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MedicineRepository extends MongoRepository<Medicine, String> {
    Optional<Medicine> findByIdAndActiveTrue(String id);
}
