package com.velvet.investing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.velvet.investing.entity.Insurance;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
    Optional<Insurance> findByUserId(String userId);
}