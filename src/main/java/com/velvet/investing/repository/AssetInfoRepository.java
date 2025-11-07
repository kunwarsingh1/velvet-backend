package com.velvet.investing.repository;

import com.velvet.investing.entity.AssetInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AssetInfoRepository extends JpaRepository<AssetInfo, Long> {
    Optional<AssetInfo> findByUserId(String userId);
}