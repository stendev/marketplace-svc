package com.example.marketplace.repository;

import com.example.marketplace.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Long> {
    @Query("""
            SELECT p FROM Price p
            WHERE p.productId = :productId
            AND p.brandId = :brandId
            AND :applicationDate BETWEEN p.rateStartDate AND p.rateEndDate
            ORDER BY p.priority DESC
            """)
    Optional<Price> findProductPriceRate(LocalDateTime applicationDate, Integer productId, Integer brandId);
}
