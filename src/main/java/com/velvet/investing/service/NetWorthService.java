package com.velvet.investing.service;

import com.velvet.investing.dto.response.NetWorthResponse;
import com.velvet.investing.dto.response.PortfolioOverviewResponse;
import com.velvet.investing.entity.AssetInfo;
import com.velvet.investing.entity.LiabilityInfo;
import com.velvet.investing.repository.AssetInfoRepository;
import com.velvet.investing.repository.LiabilityInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NetWorthService {
    
    private final AssetInfoRepository assetInfoRepository;
    private final LiabilityInfoRepository liabilityInfoRepository;
    
    public NetWorthResponse calculateNetWorth(String userId) {
        AssetInfo assets = assetInfoRepository.findByUserId(userId).orElse(null);
        LiabilityInfo liabilities = liabilityInfoRepository.findByUserId(userId).orElse(null);
        
        Double totalAssets = calculateTotalAssets(assets);
        Double totalLiabilities = calculateTotalLiabilities(liabilities);
        Double netWorth = totalAssets - totalLiabilities;
        
        return NetWorthResponse.builder()
                .totalAssets(totalAssets)
                .totalLiabilities(totalLiabilities)
                .netWorth(netWorth)
                .build();
    }
    
    private Double calculateTotalAssets(AssetInfo assets) {
        if (assets == null) return 0.0;
        return (assets.getMutualFunds() != null ? assets.getMutualFunds() : 0.0) +
               (assets.getStocks() != null ? assets.getStocks() : 0.0) +
               (assets.getFixedDeposits() != null ? assets.getFixedDeposits() : 0.0) +
               (assets.getRealEstate() != null ? assets.getRealEstate() : 0.0) +
               (assets.getGold() != null ? assets.getGold() : 0.0) +
               (assets.getCashAndBank() != null ? assets.getCashAndBank() : 0.0);
    }
    
    private Double calculateTotalLiabilities(LiabilityInfo liabilities) {
        if (liabilities == null) return 0.0;
        return (liabilities.getHomeLoan() != null ? liabilities.getHomeLoan() : 0.0) +
               (liabilities.getVehicleLoan() != null ? liabilities.getVehicleLoan() : 0.0) +
               (liabilities.getPersonalLoan() != null ? liabilities.getPersonalLoan() : 0.0) +
               (liabilities.getCreditCard() != null ? liabilities.getCreditCard() : 0.0);
    }
    
    public PortfolioOverviewResponse getPortfolioOverview(String userId) {
        AssetInfo assets = assetInfoRepository.findByUserId(userId).orElse(null);
        Double portfolioValue = calculatePortfolioValue(assets);
        
        return PortfolioOverviewResponse.builder()
                .totalAssets(portfolioValue)
                .build();
    }
    
    private Double calculatePortfolioValue(AssetInfo assets) {
        if (assets == null) return 0.0;
        return (assets.getMutualFunds() != null ? assets.getMutualFunds() : 0.0) +
               (assets.getStocks() != null ? assets.getStocks() : 0.0) +
               (assets.getFixedDeposits() != null ? assets.getFixedDeposits() : 0.0) +
               (assets.getGold() != null ? assets.getGold() : 0.0) +
               (assets.getCashAndBank() != null ? assets.getCashAndBank() : 0.0);
    }
}