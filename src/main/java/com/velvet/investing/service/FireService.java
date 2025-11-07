package com.velvet.investing.service;

import com.velvet.investing.dto.request.FireRequest;
import com.velvet.investing.dto.response.FireResponse;
import com.velvet.investing.entity.AssetInfo;
import com.velvet.investing.entity.FinancialInfo;
import com.velvet.investing.repository.AssetInfoRepository;
import com.velvet.investing.repository.FinancialInfoRepository;
import com.velvet.investing.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FireService {
    
    private final AssetInfoRepository assetInfoRepository;
    private final FinancialInfoRepository financialInfoRepository;
    private final AuthenticationService authenticationService;
    
    public FireResponse calculateFire(FireRequest request) {
        double fireNumber = request.getAnnualExpenses() * 30;
        double firePercentage = (request.getCurrentPortfolioValue() * 100) / fireNumber;
        return new FireResponse(fireNumber, firePercentage);
    }
    
    public FireResponse getUserFire(String userId) {
        FinancialInfo financialInfo = financialInfoRepository.findByUserId(userId).orElse(null);
        AssetInfo assetInfo = assetInfoRepository.findByUserId(userId).orElse(null);
        
        double annualExpenses = 0.0;
        if (financialInfo != null) {
            double monthlyExpenses = (financialInfo.getMonthlyHousing() != null ? financialInfo.getMonthlyHousing() : 0.0) +
                                   (financialInfo.getMonthlyFood() != null ? financialInfo.getMonthlyFood() : 0.0) +
                                   (financialInfo.getMonthlyTransport() != null ? financialInfo.getMonthlyTransport() : 0.0) +
                                   (financialInfo.getMonthlyOthers() != null ? financialInfo.getMonthlyOthers() : 0.0);
            annualExpenses = monthlyExpenses * 12;
        }
        
        double currentPortfolioValue = 0.0;
        if (assetInfo != null) {
            currentPortfolioValue = (assetInfo.getCashAndBank() != null ? assetInfo.getCashAndBank() : 0.0) +
                                  (assetInfo.getFixedDeposits() != null ? assetInfo.getFixedDeposits() : 0.0) +
                                  (assetInfo.getMutualFunds() != null ? assetInfo.getMutualFunds() : 0.0) +
                                  (assetInfo.getStocks() != null ? assetInfo.getStocks() : 0.0) +
                                  (assetInfo.getRealEstate() != null ? assetInfo.getRealEstate() : 0.0) +
                                  (assetInfo.getGold() != null ? assetInfo.getGold() : 0.0);
        }
        
        double fireNumber = annualExpenses * 30;
        double firePercentage = fireNumber > 0 ? (currentPortfolioValue * 100) / fireNumber : 0.0;
        
        return new FireResponse(fireNumber, firePercentage);
    }
    
    public FireResponse getUserFireByEmail(String email) {
        String userId = authenticationService.getUserIdByEmail(email);
        if (userId == null) return new FireResponse(0.0, 0.0);
        return getUserFire(userId);
    }
}