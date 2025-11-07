package com.velvet.investing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.velvet.investing.entity.OnboardingCompletion;

public interface OnboardingCompletionRepository extends JpaRepository<OnboardingCompletion, Long> {
    Optional<OnboardingCompletion> findByUserId(String userId);
}