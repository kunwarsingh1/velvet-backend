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
public class Step2Request {
    
    private String userId;
    
    private Double annualIncome;
    
    private MonthlyExpenses monthlyExpenses;
    
    @Getter
    @Setter
    public static class MonthlyExpenses {
        private Double housing;
        private Double food;
        private Double transport;
        private Double others;
    }
}