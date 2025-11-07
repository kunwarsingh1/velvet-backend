package com.velvet.investing.repository;

import com.velvet.investing.entity.LiabilityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LiabilityInfoRepository extends JpaRepository<LiabilityInfo, Long> {
    Optional<LiabilityInfo> findByUserId(String userId);
}