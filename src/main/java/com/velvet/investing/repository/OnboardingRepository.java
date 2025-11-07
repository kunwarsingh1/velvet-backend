package com.velvet.investing.repository;

import com.velvet.investing.entity.OnboardingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnboardingRepository extends JpaRepository<OnboardingData, Long> {
}
