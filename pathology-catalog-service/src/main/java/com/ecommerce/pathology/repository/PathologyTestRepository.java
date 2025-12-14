package com.ecommerce.pathology.repository;

import com.ecommerce.pathology.domain.PathologyTest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
public interface PathologyTestRepository extends MongoRepository<PathologyTest,String> {

    Optional<PathologyTest> findByIdAndActiveTrue(String id);
}
