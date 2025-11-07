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
public class MonthlyExpenseResponse {
    
    // Basic Expenses
    private Double monthlyHousing;
    private Double monthlyFood;
    private Double monthlyTransport;
    private Double monthlyOthers;
    private Double basicExpenses;
    
    // EMI Details
    private Double homeEMI;
    private Double vehicleEMI;
    private Double personalEMI;
    private Double creditCardMinPayment;
    private Double totalEmis;
    
    // Total
    private Double totalMonthlyExpense;
}