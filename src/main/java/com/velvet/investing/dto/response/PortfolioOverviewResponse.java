package com.velvet.investing.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioOverviewResponse {
    private Double totalAssets;
    private Double totalMutualFunds;
    private Double totalStocks;
    private Double totalRealEstate;
    private Double totalFixedDeposits;
    private Double totalGold;
    private Double totalCashAndBank;
    private Double mutualFundsAllocation;
    private Double stocksAllocation;
    private Double realEstateAllocation;
    private Double fixedDepositsAllocation;
    private Double goldAllocation;
    private Double cashAndBankAllocation;
}