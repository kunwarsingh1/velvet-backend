package com.velvet.investing.service;

import com.velvet.investing.dto.response.AssetResponse;
import com.velvet.investing.entity.AssetInfo;
import com.velvet.investing.repository.AssetInfoRepository;
import com.velvet.investing.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssetService {
    
    private final AssetInfoRepository assetInfoRepository;
    private final AuthenticationService authenticationService;
    
    public AssetResponse getUserAssets(String userId) {
        AssetInfo assetInfo = assetInfoRepository.findByUserId(userId).orElse(null);
        
        if (assetInfo == null) {
            return AssetResponse.builder()
                    .userId(userId)
                    .cashAndBank(0.0)
                    .fixedDeposits(0.0)
                    .mutualFunds(0.0)
                    .stocks(0.0)
                    .realEstate(0.0)
                    .gold(0.0)
                    .totalAssets(0.0)
                    .build();
        }
        
        Double cashAndBank = assetInfo.getCashAndBank() != null ? assetInfo.getCashAndBank() : 0.0;
        Double fixedDeposits = assetInfo.getFixedDeposits() != null ? assetInfo.getFixedDeposits() : 0.0;
        Double mutualFunds = assetInfo.getMutualFunds() != null ? assetInfo.getMutualFunds() : 0.0;
        Double stocks = assetInfo.getStocks() != null ? assetInfo.getStocks() : 0.0;
        Double realEstate = assetInfo.getRealEstate() != null ? assetInfo.getRealEstate() : 0.0;
        Double gold = assetInfo.getGold() != null ? assetInfo.getGold() : 0.0;
        Double totalAssets = cashAndBank + fixedDeposits + mutualFunds + stocks + realEstate + gold;
        
        return AssetResponse.builder()
                .userId(userId)
                .cashAndBank(cashAndBank)
                .fixedDeposits(fixedDeposits)
                .mutualFunds(mutualFunds)
                .stocks(stocks)
                .realEstate(realEstate)
                .gold(gold)
                .totalAssets(totalAssets)
                .build();
    }
    
    public AssetResponse getUserAssetsByEmail(String email) {
        String userId = authenticationService.getUserIdByEmail(email);
        if (userId == null) return createEmptyResponse("USER_NOT_FOUND");
        return getUserAssets(userId);
    }
    
    private AssetResponse createEmptyResponse(String userId) {
        return AssetResponse.builder()
                .userId(userId)
                .cashAndBank(0.0)
                .fixedDeposits(0.0)
                .mutualFunds(0.0)
                .stocks(0.0)
                .realEstate(0.0)
                .gold(0.0)
                .totalAssets(0.0)
                .build();
    }
}