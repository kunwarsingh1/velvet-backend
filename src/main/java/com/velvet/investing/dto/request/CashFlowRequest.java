package com.velvet.investing.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CashFlowRequest {
    @NotNull
    @Positive
    private BigDecimal monthlySalary;
    
    @NotNull
    @Positive
    private BigDecimal monthlyExpense;
}