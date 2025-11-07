package com.velvet.investing.dto.request;

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
public class Step3Request {
    
    private String userId;
    
    private CurrentAssets currentAssets;
    
    @Getter
    @Setter
    public static class CurrentAssets {
        private Double mutualFunds;
        private Double stocks;
        private Double fixedDeposits;
        private Double realEstate;
        private Double gold;
        private Double cashAndBank;
    }
}