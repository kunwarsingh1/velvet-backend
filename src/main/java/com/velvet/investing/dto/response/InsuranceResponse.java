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
public class InsuranceResponse {
    
    private String userId;
    private Double termLife;
    private Double health;
    private Double criticalIllness;
    private Double totalInsurance;
}