package com.velvet.investing.service;

import org.springframework.stereotype.Service;

import com.velvet.investing.dto.response.ProjectionResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectionService {
    
    private final AssetService assetService;
    private final LiabilityService liabilityService;
    private final UserService userService;
    
    public ProjectionResponse getUserProjectionByEmail(String email) {
        String userId = userService.getUserProfileByEmail(email).getId();
        return getUserProjection(userId);
    }
    
    public ProjectionResponse getUserProjection(String userId) {
        // Get current assets and liabilities
        Double totalAssets = assetService.getUserAssets(userId).getTotalAssets();
        Double totalLiabilities = liabilityService.getUserLiabilities(userId).getTotalLiabilities();
        Double currentNetWorth = totalAssets - totalLiabilities;
        
        // Assume 12% annual return for projections
        Double expectedReturn = 12.0;
        Double monthlyReturn = expectedReturn / 100 / 12;
        
        // Calculate projections with compound interest
        Double projectedNetWorth10Years = currentNetWorth * Math.pow(1 + expectedReturn/100, 10);
        Double projectedNetWorth20Years = currentNetWorth * Math.pow(1 + expectedReturn/100, 20);
        Double projectedNetWorth30Years = currentNetWorth * Math.pow(1 + expectedReturn/100, 30);
        
        // Calculate monthly investment required for 1 crore in 20 years
        Double targetAmount = 10000000.0; // 1 crore
        Double monthlyInvestmentRequired = (targetAmount - currentNetWorth * Math.pow(1 + expectedReturn/100, 20)) / 
                                         (((Math.pow(1 + monthlyReturn, 240) - 1) / monthlyReturn));
        
        return ProjectionResponse.builder()
                .userId(userId)
                .currentNetWorth(currentNetWorth)
                .projectedNetWorth10Years(projectedNetWorth10Years)
                .projectedNetWorth20Years(projectedNetWorth20Years)
                .projectedNetWorth30Years(projectedNetWorth30Years)
                .monthlyInvestmentRequired(Math.max(0, monthlyInvestmentRequired))
                .expectedReturn(expectedReturn)
                .build();
    }
}