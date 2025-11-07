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
public class LiabilityResponse {
    
    private String userId;
    private Double homeLoan;
    private Double vehicleLoan;
    private Double personalLoan;
    private Double creditCard;
    private Double homeEMI;
    private Double vehicleEMI;
    private Double personalEMI;
    private Double creditCardMinPayment;
    private Double totalLiabilities;
    private Double totalEMIs;
}