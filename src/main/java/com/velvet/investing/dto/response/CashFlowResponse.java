package com.velvet.investing.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CashFlowResponse {
    private BigDecimal monthlySalary;
    private BigDecimal monthlyExpense;
    private BigDecimal netDeficit;
    private BigDecimal savingRate;
    private BigDecimal annualSurplus;
}