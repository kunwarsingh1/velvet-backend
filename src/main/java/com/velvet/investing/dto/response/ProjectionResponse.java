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
public class ProjectionResponse {
    
    private String userId;
    private Double currentNetWorth;
    private Double projectedNetWorth10Years;
    private Double projectedNetWorth20Years;
    private Double projectedNetWorth30Years;
    private Double monthlyInvestmentRequired;
    private Double expectedReturn;
}