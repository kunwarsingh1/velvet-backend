package com.velvet.investing.service;

import com.velvet.investing.dto.request.PortfolioOverviewRequest;
import com.velvet.investing.dto.response.PortfolioOverviewResponse;
import com.velvet.investing.entity.AssetInfo;
import com.velvet.investing.repository.AssetInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortfolioOverviewService {
    
    private final AssetInfoRepository assetInfoRepository;
    
    public PortfolioOverviewResponse getPortfolioOverview(PortfolioOverviewRequest request) {
        AssetInfo assets = assetInfoRepository.findByUserId(request.getUserId()).orElse(null);
        
        if (assets == null) {
            return PortfolioOverviewResponse.builder()
                    .totalAssets(0.0)
                    .build();
        }
        
        Double mutualFunds = assets.getMutualFunds() != null && assets.getMutualFunds() > 0 ? assets.getMutualFunds() : 0.0;
        Double stocks = assets.getStocks() != null && assets.getStocks() > 0 ? assets.getStocks() : 0.0;
        Double realEstate = assets.getRealEstate() != null && assets.getRealEstate() > 0 ? assets.getRealEstate() : 0.0;
        Double fixedDeposits = assets.getFixedDeposits() != null && assets.getFixedDeposits() > 0 ? assets.getFixedDeposits() : 0.0;
        Double gold = assets.getGold() != null && assets.getGold() > 0 ? assets.getGold() : 0.0;
        Double cashAndBank = assets.getCashAndBank() != null && assets.getCashAndBank() > 0 ? assets.getCashAndBank() : 0.0;
        
        Double totalAssets = mutualFunds + stocks + realEstate + fixedDeposits + gold + cashAndBank;
        
        return PortfolioOverviewResponse.builder()
                .totalAssets(totalAssets)
                .totalMutualFunds(mutualFunds > 0 ? mutualFunds : null)
                .totalStocks(stocks > 0 ? stocks : null)
                .totalRealEstate(realEstate > 0 ? realEstate : null)
                .totalFixedDeposits(fixedDeposits > 0 ? fixedDeposits : null)
                .totalGold(gold > 0 ? gold : null)
                .totalCashAndBank(cashAndBank > 0 ? cashAndBank : null)
                .mutualFundsAllocation(mutualFunds > 0 && totalAssets > 0 ? (mutualFunds / totalAssets) * 100 : null)
                .stocksAllocation(stocks > 0 && totalAssets > 0 ? (stocks / totalAssets) * 100 : null)
                .realEstateAllocation(realEstate > 0 && totalAssets > 0 ? (realEstate / totalAssets) * 100 : null)
                .fixedDepositsAllocation(fixedDeposits > 0 && totalAssets > 0 ? (fixedDeposits / totalAssets) * 100 : null)
                .goldAllocation(gold > 0 && totalAssets > 0 ? (gold / totalAssets) * 100 : null)
                .cashAndBankAllocation(cashAndBank > 0 && totalAssets > 0 ? (cashAndBank / totalAssets) * 100 : null)
                .build();
    }
}