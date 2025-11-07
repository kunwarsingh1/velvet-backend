package com.velvet.investing.repository;

import com.velvet.investing.entity.FinancialInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FinancialInfoRepository extends JpaRepository<FinancialInfo, Long> {
    Optional<FinancialInfo> findByUserId(String userId);
}