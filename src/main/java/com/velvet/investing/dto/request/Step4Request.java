package com.velvet.investing.dto.request;

import java.util.List;

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
public class Step4Request {
    
    private String userId;
    
    private CurrentLiabilities currentLiabilities;
    
    private List<LoanDto> loans;
    
    @Getter
    @Setter
    public static class CurrentLiabilities {
        private Double homeLoan;
        private Double vehicleLoan;
        private Double personalLoan;
        private Double creditCard;
        private Double homeEMI;
        private Double vehicleEMI;
        private Double personalEMI;
        private Double creditCardMinPayment;
    }
    
    @Getter
    @Setter
    public static class LoanDto {
        private String id;
        private String type;
        private String customName;
        private Double outstandingAmount;
        private Double emi;
        private Integer tenure;
    }
}