package com.velvet.investing.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetResponse {
    
    private String userId;
    private Double cashAndBank;
    private Double fixedDeposits;
    private Double mutualFunds;
    private Double stocks;
    private Double realEstate;
    private Double gold;
    private Double totalAssets;
}